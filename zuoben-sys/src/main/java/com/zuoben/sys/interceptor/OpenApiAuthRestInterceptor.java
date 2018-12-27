package com.zuoben.sys.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * openApi鉴权拦截器
 */
public class OpenApiAuthRestInterceptor extends HandlerInterceptorAdapter {
//    @Autowired
//    private UserAuthConfig userAuthConfig;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        // 配置该注解，说明不进行用户拦截
//        IgnoreUserToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreUserToken.class);
//        if (annotation == null) {
//            annotation = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);
//        }
//        if (annotation != null) {
//            return super.preHandle(request, response, handler);
//        }
//        String userId = request.getHeader(userAuthConfig.getUserIdHeader());
//        String userName = request.getHeader(userAuthConfig.getUserNameHeader());
//        if (userId == null || userName == null) {
//            throw new OpenApiSignException("用户信息为空");
//        }
//        BaseContextHandler.setUsername(userName);
//        BaseContextHandler.setUserID(userId);
//        return super.preHandle(request, response, handler);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        BaseContextHandler.remove();
//        super.afterCompletion(request, response, handler, ex);
//    }
}
