package com.example.hj.retrofit.Retrofit;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {
      public static final String url = "https://2018-anyfood-dev.workservice.kr:773/";
      public final String API_url = "http://jsonplaceholder.typicode.com";
      @GET("/posts/{userId}")
      Call<RetrofitRepo> getFirst(@Path("userId") String id);

      @GET("/posts")
      Call<List<RetrofitRepo>> getSecond(@Query("userId") String id);

      @FormUrlEncoded
      @POST("/posts")
      Call<RetrofitRepo> postFirst(@FieldMap HashMap<String, Object> parameters);




    /**
     * @details GET/POST/DELETE/PUT 메소드들을 인터페이스에 구현하여 사용할 수 있다.
     * @param act
     * @return
     */
    @GET("kr/test/test.php")
    Call<RetrofitRepo> getGet(@Query("act") String act);


//    @FormUrlEncoded
//    @POST("test.php")
//    Call<RetrofitRepo> getPost(@Path("act") String act);

    /**
     * @POST("/posts") : POST 방식의 통신입니다.
     * @FieldMap HashMap<String, Object> parameters : Field 형식을 통해 넘겨주는 값들이 여러개일 때 FieldMap을 사용합니다. Retrofit에서는 Map보다는 HashMap형식을 쓰기를 권장하고 있습니다.
     * @FormUrlEncoded : Field 형식을 사용할 때는 Form이 Encoding되어야 합니다. 따라서 FormUrlEncoded라는 Annotation을 해주어야 합니다.
     * @Field 형식의 경우에는 주로 POST 방식의 통신을 할때 사용합니다. GET 방식에서는 사용이 불가능합니다.
     * @param param 요청에 필요한 값들.
     * @return Data 객체를 JSON 형태로 반환.
     */
    @FormUrlEncoded
    @POST("kr/test/test.php?act=dongju")
    Call<RetrofitRepo> getComments(@FieldMap HashMap<String, Object> param);



}
