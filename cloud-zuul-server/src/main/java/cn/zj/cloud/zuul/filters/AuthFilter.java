package cn.zj.cloud.zuul.filters;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonHttpResponse;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cn.zj.cloud.constant.Constant;
import cn.zj.cloud.enums.HttpMethodType;
import cn.zj.cloud.model.Response;
import cn.zj.cloud.util.StringUtil;
import cn.zj.cloud.zuul.feign.AuthServiceFeign;
import feign.FeignException.Unauthorized;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class AuthFilter extends ZuulFilter {
	@Autowired
	AuthServiceFeign auth;

	@Override
	public boolean shouldFilter() {
		boolean checkResult = true;

		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		String uri = request.getRequestURI();
		String requestMethod = request.getMethod();
		// if request method is options skip filter
		if(HttpMethodType.OPTIONS.getType().equalsIgnoreCase(requestMethod)) {
			return false;
		}

		// the following url could skip filter
		if(HttpMethodType.POST.getType().equalsIgnoreCase(requestMethod)) {
			if(Constant.SKIP_FILTER_URL_LOGIN.equals(uri)) {
				checkResult = false;
			} else if (Constant.SKIP_FILTER_URL_USER.equals(uri)) {
				checkResult = false;
			}
		} else if (HttpMethodType.PUT.getType().equals(requestMethod)) {
			String urlPattern="/user/[0-9]*";
			boolean isMatch = Pattern.matches(urlPattern, uri);
			if(isMatch) {
				checkResult = false;
			}
		}

		return checkResult;
	}

	@Override
	public Object run() throws ZuulException {

		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = (HttpServletRequest)requestContext.getRequest();
		// get tkn from request header
		String token = request.getHeader("tkn");


		// if could not get token, response unauthorized info  
		if(token == null) {
			Response response = new Response();
			response.setStatus(HttpStatus.SC_UNAUTHORIZED);
			response.setCode(Constant.CODE_ZERO);
			response.setMessage(Constant.AUTH_TOKEN_IS_EMPTY);
			
			requestContext.setSendZuulResponse(false);
			requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
			requestContext.setResponseBody(StringUtil.createResponseBody(response));
			requestContext.getResponse().setContentType(MediaType.APPLICATION_JSON.toString());
			
			// if token was gotten, verify the token 
		} else {
			try {
				// invoke micro auth service to verify the token
				ResponseEntity<Response> res = auth.verify(token);
				if(res.getBody() != null) {
					// if get response content from micro auth service, 
					Response resp = (Response)res.getBody();
					// if verify fail
					if(Constant.CODE_ZERO.equals(resp.getCode())) {
						requestContext.setSendZuulResponse(false);
						requestContext.setResponseStatusCode(res.getStatusCodeValue());
						requestContext.setResponseBody(StringUtil.createResponseBody(res.getBody()));
						requestContext.getResponse().setContentType(MediaType.APPLICATION_JSON.toString());
					}

				} else {
					// if could not get response content from micro auth service, 
					// response server error from zuul service 
					serverErrorResponse(requestContext);
				}
				
				// if micro auth service reponse status 401, it would be throw as feignException
				// so it should be catch rewrite response info to zuul service
			} catch(Unauthorized e) {
				Response response = new Response();
				response.setStatus(e.status());
				response.setCode(Constant.CODE_ZERO);
				response.setMessage(Constant.TOKEN_VERIFY_INVILAD);
				
				requestContext.setSendZuulResponse(false);
				requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
				requestContext.setResponseBody(StringUtil.createResponseBody(response));
				requestContext.getResponse().setContentType(MediaType.APPLICATION_JSON.toString());
				
				// if any exception was cathced, response server error from zuul service 
			} catch(Exception e) {
				serverErrorResponse(requestContext);
			}
		}

		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	// Response server error response
	private void serverErrorResponse(RequestContext requestContext) {
		Response response = new Response();
		response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		response.setCode(Constant.CODE_ZERO);
		response.setMessage(Constant.INTERNAL_SERVER_ERROR);
		
		requestContext.setSendZuulResponse(false);
		requestContext.setResponseStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		requestContext.setResponseBody(StringUtil.createResponseBody(response));
		requestContext.getResponse().setContentType(MediaType.APPLICATION_JSON.toString());
	}

}
