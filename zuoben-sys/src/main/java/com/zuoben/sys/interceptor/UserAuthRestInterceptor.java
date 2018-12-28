package com.zuoben.sys.interceptor;

import com.zuoben.annotation.IgnoreUserToken;
import com.zuoben.context.BaseContextHandler;
import com.zuoben.sys.client.AuthHttpClient;
import com.zuoben.sys.config.UserAuthConfig;
import com.zuoben.vo.user.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户鉴权拦截器
 */
public class UserAuthRestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserAuthConfig userAuthConfig;
    @Autowired
    private AuthHttpClient authHttpClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 配置该注解，说明不进行用户拦截
        IgnoreUserToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreUserToken.class);
        if (annotation == null) {
            annotation = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);
        }
        if (annotation != null) {
            return super.preHandle(request, response, handler);
        }
        String token = request.getHeader(userAuthConfig.getTokenHeader());
        if (StringUtils.isEmpty(token)) {
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(userAuthConfig.getTokenHeader())) {
                        token = cookie.getValue();
                    }
                }
            }
        }
        UserInfo userInfo = authHttpClient.getInfoFromToken(token);
        BaseContextHandler.setUsername(userInfo.getUsername());
        BaseContextHandler.setUserID(userInfo.getId());
        BaseContextHandler.setUserPhone(userInfo.getPhonenum());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
