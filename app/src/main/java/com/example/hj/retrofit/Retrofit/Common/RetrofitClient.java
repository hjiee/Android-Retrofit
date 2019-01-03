package com.example.hj.retrofit.Retrofit.Common;

import com.example.hj.retrofit.ConstantDefine;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final int TIMEOUT_CONNECTION = 50000;
    private static Retrofit objRetrofit;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Retrofit Singleton Instance
    public static Retrofit getRetrofitInstance()
    {
        if(objRetrofit == null) {
            synchronized (RetrofitClient.class) {
                if(objRetrofit == null) {

//                    쿠키,세션 유지를위해 사용
//                    OkHttpClient.Builder objOkHttpClientBuilder = new OkHttpClient.Builder()
//                        .readTimeout(TIMEOUT_CONNECTION, TimeUnit.MILLISECONDS)
//                        .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.MILLISECONDS);
//                    objOkHttpClientBuilder.interceptors().add(new RetrofitCustomInterceptor());

                    objRetrofit = new Retrofit.Builder()
                            .baseUrl(ConstantDefine.DEF_BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
//                .client(objOkHttpClientBuilder.build())
                            .build() ;
                }
            }
        }
        return objRetrofit;
    }

    private static class RetrofitCustomInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain var1) throws IOException {
            String var2 = System.getProperty("http.agent");
            Request.Builder var3 = var1.request().newBuilder();
            var3.removeHeader("User-Agent").addHeader("User-Agent", var2);
            return var1.proceed(var3.build());
        }
    }
}
