package com.example.hj.retrofit;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserSchema {

    public static final int TIMEOUT_CONNECTION = 50000;
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //region Server URL
    public static final String DEF_BASE_URL = "https://2018-anyfood-dev.workservice.kr:773/kr/test/";
    public static final String DEF_SUB_END_POINT = "test.php";
    public static final String DEF_SUB_END_POINT2 = "test2.php";
    //endregion
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static Retrofit getRetrofitInstance(Context context)
    {

//        OkHttpClient.Builder objOkHttpClientBuilder = new OkHttpClient.Builder()
//                .readTimeout(TIMEOUT_CONNECTION, TimeUnit.MILLISECONDS)
//                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.MILLISECONDS);
//        objOkHttpClientBuilder.interceptors().add(new RetrofitCustomInterceptor(context));

        Retrofit objRetrofit = new Retrofit.Builder()
                .baseUrl(DEF_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(objOkHttpClientBuilder.build())
                .build() ;

        return objRetrofit;
    }


}
