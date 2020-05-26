package cn.zj.cloud.zuul.filters;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonHttpResponse;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import cn.zj.cloud.constant.Constant;
import cn.zj.cloud.enums.HttpMethodType;
import cn.zj.cloud.model.Response;
import cn.zj.cloud.util.StringUtil;
import cn.zj.cloud.zuul.feign.AuthServiceFeign;
import cn.zj.cloud.zuul.feign.model.AuthInfo;

@Component
public class AddTokenFilter extends ZuulFilter {
	@Autowired
	AuthServiceFeign auth;


	@Override
	public boolean shouldFilter() {
		boolean checkResult = false;

		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		String uri = request.getRequestURI();
		String requestMethod = request.getMethod();
		// if request method is options skip filter
		if(HttpMethodType.OPTIONS.getType().equalsIgnoreCase(requestMethod)) {
			return false;
		}
		// the following url should filter
		if(HttpMethodType.POST.getType().equalsIgnoreCase(requestMethod)) {
			if(Constant.SKIP_FILTER_URL_LOGIN.equals(uri)) {
				checkResult = true;
			}
		}

		return checkResult;
	}

	@Override
	public Object run() throws ZuulException {

		RequestContext requestContext = RequestContext.getCurrentContext();
		Object zuulResponse = requestContext.get("zuulResponse");
		if (zuulResponse != null) {
	         try {
	        	RibbonHttpResponse resp = (RibbonHttpResponse) zuulResponse;
				String body = IOUtils.toString(resp.getBody());
				ObjectMapper jsonMapper = new ObjectMapper();
				Response res = jsonMapper.readValue(body, Response.class);
				// if user successful login 
				if(Constant.CODE_ONE.equals(res.getCode())) {
					// get user id as token claim
					String userId = res.getBusiness().get(Constant.BUSINESS_DATA_ID).toString();
					// invoke micro auth service to issue token
					AuthInfo authInf = new AuthInfo();
					authInf.setUserId(userId);
					ResponseEntity<Response> resEntigy = auth.issueToken(authInf);
					String tkn = resEntigy.getHeaders().getFirst("tkn");
					requestContext.getResponse().setHeader("tkn", tkn);
					
					// rewrite response and sent by zuul service
					requestContext.setResponseStatusCode(res.getStatus());
					requestContext.setResponseBody(StringUtil.createResponseBody(res));
					return null;
				} else {
					requestContext.setResponseBody(StringUtil.createResponseBody(res));
					requestContext.setResponseStatusCode(res.getStatus());
				}
				
				// if any exception was cathced, response server error from zuul service 
			} catch (Exception e) { 
				serverErrorResponse(requestContext);
			}
	         
	      // if could not get zuulResponse, response server error from zuul service 
		} else {
			serverErrorResponse(requestContext);
		}

		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.POST_TYPE;
	}

	@Override
	public int filterOrder() {
		return 0;
	}
	
	// Response server error info
	private void serverErrorResponse(RequestContext requestContext) {
		Response response = new Response();
		response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		response.setCode(Constant.CODE_ZERO);
		response.setMessage(Constant.INTERNAL_SERVER_ERROR);

		requestContext.setSendZuulResponse(false);
		requestContext.setResponseStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		requestContext.setResponseBody(StringUtil.createResponseBody(response));
		requestContext.getResponse().setContentType(MediaType.APPLICATION_JSON.toString());
		requestContext.setResponseStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
	}
}
