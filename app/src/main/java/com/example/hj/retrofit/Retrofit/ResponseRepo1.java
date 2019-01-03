package com.example.hj.retrofit.Retrofit;

import com.example.hj.retrofit.Retrofit.Common.CommonResponseRepo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public class ResponseRepo1 {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //region ResponseInterface1
    public interface ResponseInterface1 {
        /**
         * @Gson 자바 객체와 JSON 간의 직렬화 및 역직렬화를 위한 오픈소스 자바 라이브러리이다.
         * @GET GET 방식의 통신입니다.
         * @Path 값의 경로를 지정한다. {}를 이용하여 데이터값을 입력받을 수 있다.
         * @Query 데이터인자 값을 입력 받는다.
         * @details GET/POST/DELETE/PUT 메소드들을 인터페이스에 구현하여 사용할 수 있다.
         * @param param 요청에 필요한 값.
         * @return Data 객체를 JSON 형태로 반환
         */
        @GET
        Call<ResponseRepo1> GetComments(@Url String strURL, @QueryMap HashMap<String,Object> param);

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
        Call<ResponseRepo1> PostComments(@Url String strURL, @FieldMap HashMap<String, Object> param);
    }
    //endregion ResponseInterface1
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // exntends CoomonResponse
    public String getAction_result()            { return objresult.getAction_result(); }
    public String getAction_success_message()   { return objresult.getAction_success_message(); }
    public String getAction_failure_code()      { return objresult.getAction_failure_code(); }
    public String getAction_failure_reason()    { return objresult.getAction_failure_reason(); }

    public String getName()     { return objinfo.getName(); }
    public String getPhone()    { return objinfo.getPhone(); }

    @SerializedName("result")   @Expose private Result objresult;
    @SerializedName("info")     @Expose private Info objinfo;
    public Result getResponseResult() {return objresult;}
    public Info getResponseInfo() { return objinfo; }

    public class Result extends CommonResponseRepo implements Serializable {}
    public class Info extends CommonResponseRepo implements Serializable {}
}


