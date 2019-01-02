package com.example.hj.retrofit;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserSchema {

    public static final int TIMEOUT_CONNECTION = 50000;
    private static Retrofit objRetrofit;
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //region Server URL
    public static final String DEF_BASE_URL = "https://2018-anyfood-dev.workservice.kr:773/kr/test/";
    public static final String DEF_SUB_END_POINT = "test.php";
    public static final String DEF_SUB_END_POINT2 = "test2.php";
    public static final int originMethodGet = 0; // Get
    public static final int originMethodPost = 1; // Post
    //endregion
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private UserSchema()
    {

    }

    // Singleton
    public static Retrofit getRetrofitInstance()
    {
        if(objRetrofit == null) {
            synchronized (UserSchema.class) {
                if(objRetrofit == null) {

//                    쿠키,세션 유지를위해 사용
//                    OkHttpClient.Builder objOkHttpClientBuilder = new OkHttpClient.Builder()
//                        .readTimeout(TIMEOUT_CONNECTION, TimeUnit.MILLISECONDS)
//                        .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.MILLISECONDS);
//                    objOkHttpClientBuilder.interceptors().add(new RetrofitCustomInterceptor(context));

                    objRetrofit = new Retrofit.Builder()
                            .baseUrl(DEF_BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
//                .client(objOkHttpClientBuilder.build())
                            .build() ;
                }
            }
        }
        return objRetrofit;
    }


}
