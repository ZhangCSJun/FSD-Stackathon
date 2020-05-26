package cn.zj.cloud.zuul.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import cn.zj.cloud.enums.HttpMethodType;

@Component
public class AllowOriginFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response = ctx.getResponse();
        HttpServletRequest request = ctx.getRequest();
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "x-access-token, content-type, tkn");
        response.setHeader("Access-Control-Expose-Headers", "X-forwared-port, X-forwarded-host, tkn");
        response.setHeader("Vary", "Origin,Access-Control-Request-Method,Access-Control-Request-Headers");
        // 跨域请求一共会进行两次请求 先发送options 是否可以请求
        if (HttpMethodType.OPTIONS.getType().equalsIgnoreCase(request.getMethod())) {
            ctx.setSendZuulResponse(false); //验证请求不进行路由
            ctx.setResponseStatusCode(HttpStatus.OK.value());//返回验证成功的状态码
            return null;
        }
        ctx.setSendZuulResponse(true); //对请求进行路由
        ctx.setResponseStatusCode(HttpStatus.OK.value());
        return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return - 1;
	}

}
