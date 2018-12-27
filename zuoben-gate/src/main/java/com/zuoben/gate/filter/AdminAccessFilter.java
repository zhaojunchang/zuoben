package com.zuoben.gate.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.zuoben.constant.CommonConstants;
import com.zuoben.context.BaseContextHandler;
import com.zuoben.exception.ZuobenException;
import com.zuoben.gate.feign.IUserService;
import com.zuoben.gate.feign.UserAuthFeign;
import com.zuoben.jwt.IJWTInfo;
import com.zuoben.jwt.JWTHelper;
import com.zuoben.util.resultUtils.JsonResult;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class AdminAccessFilter extends ZuulFilter {
    private Logger logger = LoggerFactory.getLogger(AdminAccessFilter.class);
    @Resource
    @Lazy
    private IUserService userService;

    @Value("${gate.ignore.startWith}")
    private String startWith;
    @Value("${gate.ignore.contains}")
    private String contains;
    @Value("${zuul.prefix}")
    private String zuulPrefix;

    @Resource
    private UserAuthFeign userAuthFeign;
    /**
     * 返回过滤器类型
     * @return
     *  pre：可以在请求被路由之前调用
     *  routing：在路由请求时候被调用
     *  post：在routing和error过滤器之后被调用
     *  error：处理请求时发生错误时被调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 通过int值来定义过滤器的执行顺序 , 数字越小优先级越高
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断过滤器是否执行 , 返回false则不执行该过滤器
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     *  ctx.setSendZuulResponse(false)令zuul不允许请求，
     *  ctx.setResponseStatusCode(401)设置了其返回的错误码
     *  ctx.setResponseBody(body)编辑返回body内容
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        final String requestUri = request.getRequestURI().substring(zuulPrefix.length());
        final String method = request.getMethod();
        // 不进行拦截的地址
        if (isStartWith(requestUri) || isContains(requestUri)) {
            return null;
        }
        try {
            IJWTInfo user = getJWTUser(request, ctx);
            //判断用户状态
            JsonResult userState = userService.getUserStateByUserId(Integer.parseInt(user.getId()));
            if (!userState.isSuccess()) {
                setFailedRequest(ctx, JSON.toJSONString(userState));
                return null;
            }

            BaseContextHandler.setUsername(user.getUserName());
            BaseContextHandler.setUserID(user.getId());
            BaseContextHandler.setUserPhone(user.getPhone());
            BaseContextHandler.setCId(user.getCId());
        } catch (Exception e) {
            setFailedRequest(ctx, JSON.toJSONString(new JsonResult<String>(CommonConstants.TOKEN_ERROR_CODE, e.getMessage())));
        }
        return null;
    }

    /**
     * 返回session中的用户信息
     *
     * @param request
     * @param ctx
     * @return
     */
    private IJWTInfo getJWTUser(HttpServletRequest request, RequestContext ctx) throws Exception {
        String authToken = request.getHeader("Authorization");
        if (StringUtils.isBlank(authToken)) {
            authToken = request.getParameter("token");
        }

        if(StringUtils.isEmpty(authToken) || authToken.toLowerCase().equals("null")) {
            throw new ZuobenException("User token is null or empty!");
        }

        ctx.addZuulRequestHeader("Authorization", authToken);
        BaseContextHandler.setToken(authToken);
        IJWTInfo ijwtInfo = null;

        JsonResult<byte[]> resp = userAuthFeign.getUserPublicKey();

        if (resp.isSuccess()) {
            try {
                ijwtInfo = JWTHelper.getInfoFromToken(authToken, resp.getData());
            } catch (ExpiredJwtException ex) {
                throw new ZuobenException("User token expired!");
            } catch (SignatureException ex) {
                throw new ZuobenException("User token signature error!");
            } catch (IllegalArgumentException ex) {
                throw new ZuobenException("User token is null or empty!");
            }
        }
        return ijwtInfo;
    }

    /**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        for (String s : startWith.split(",")) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * url是否包含需要放行的字符串
     */
    private boolean isContains(String requestUri) {
        for (String s : contains.split(",")) {
            if (requestUri.contains(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 网关抛异常
     *
     * @param body
     */
    private void setFailedRequest(RequestContext ctx, String body) {
        logger.debug("Reporting error ({})", body);
        ctx.setResponseStatusCode(200);
        ctx.getResponse().setContentType("application/json;charset=UTF-8");
        if (ctx.getResponseBody() == null) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody(body);
        }
    }
}
