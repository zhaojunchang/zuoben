package com.zuoben.sys.interceptor;

//import okhttp3.Interceptor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;

/**
 * 使用拦截器对远程请求进行处理,在header里增加token
 */
//@Component
//public class OkHttpTokenInterceptor implements Interceptor {
//    Logger logger = LoggerFactory.getLogger(OkHttpTokenInterceptor.class);
//    @Autowired
//    @Lazy
//    private UserAuthConfig userAuthConfig;
//
//
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request newRequest = null;
//        newRequest = chain.request()
//                .newBuilder()
//                .header(userAuthConfig.getTokenHeader(), BaseContextHandler.getToken())
//                .build();
//
//        return chain.proceed(newRequest);
//    }
//}
