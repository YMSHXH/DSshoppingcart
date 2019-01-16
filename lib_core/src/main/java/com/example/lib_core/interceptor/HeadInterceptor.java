package com.example.lib_core.interceptor;

import com.example.lib_core.utils.ShapedP;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeadInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        //请求对象，原始请求对象，用户名密码
        Request request = chain.request();

        //构建者 保留原来的请求
        Request.Builder builder = request.newBuilder();
        builder.addHeader("userId",ShapedP.getmInstance().getSP("userId"));
        builder.addHeader("sessionId",ShapedP.getmInstance().getSP("sessionId"));

        Request newRequest = builder.build();

        //相应对象
        Response proceed = chain.proceed(newRequest);

        return proceed;
    }
}
