package com.example.hj.retrofit.Retrofit;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public class ResponseRepo2 {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //region ResponseInterface2
    public interface ResponseInterface2 {
        /**
         * @Gson 자바 객체와 JSON 간의 직렬화 및 역직렬화를 위한 오픈소스 자바 라이브러리이다.
         * @GET GET 방식의 통신입니다.
         * @Path 값의 경로를 지정한다. {}를 이용하여 데이터값을 입력받을 수 있다.
         * @Query 데이터인자 값을 입력 받는다.
         * @details GET/POST/DELETE/PUT 메소드들을 인터페이스에 구현하여 사용할 수 있다.
         * @param act 요청에 필요한 값.
         * @return Data 객체를 JSON 형태로 반환
         */
        @GET
        Call<CommonResponseRepo> GetComments(@Url String strURL, @Query("act") Object act);

        /**
         * @POST("/posts") : POST 방식의 통신입니다.
         * @FieldMap HashMap<String, Object> parameters : Field 형식을 통해 넘겨주는 값들이 여러개일 때 FieldMap을 사용합니다. Retrofit에서는 Map보다는 HashMap형식을 쓰기를 권장하고 있습니다.
         * @FormUrlEncoded : Field 형식을 사용할 때는 Form이 Encoding되어야 합니다. 따라서 FormUrlEncoded라는 Annotation을 해주어야 합니다.
         *                     FormUrlEncoded를 추가시켜주어야 정상적으로 parameter가 붙어 전송된다.
         * @Field 형식의 경우에는 주로 POST 방식의 통신을 할때 사용합니다. GET 방식에서는 사용이 불가능합니다.
         * @param param 요청에 필요한 값들.
         * @return Data 객체를 JSON 형태로 반환.
         */
        @FormUrlEncoded
        @POST
        Call<CommonResponseRepo> PostComments(@Url String strURL, @FieldMap HashMap<String, Object> param);
    }
    //endregion CommonResponseInterface
    ////////////////////////////////////////////////////////////////////////////////////////////////
}
