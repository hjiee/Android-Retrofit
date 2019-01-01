package com.example.hj.retrofit.Retrofit;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RetrofitCustomInterceptor implements Interceptor {
    Context objContext = null;
    /**
     * @Intercepter Retrofit에서 Okhttp를 이용하여 실행시마다 항상 같은 쿠키값을 유지하는 방법
     */
    public RetrofitCustomInterceptor(Context context) {
        this.objContext = context;
    }

    public Response intercept(Chain var1) throws IOException {
        String var2 = System.getProperty("http.agent");
        Request.Builder var3 = var1.request().newBuilder();
        var3.removeHeader("User-Agent").addHeader("User-Agent", var2);
        return var1.proceed(var3.build());
    }
}
