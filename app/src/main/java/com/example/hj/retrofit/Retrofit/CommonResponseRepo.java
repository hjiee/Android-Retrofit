package com.example.hj.retrofit.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public class CommonResponseRepo {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //region CommonResponseInterface
    public interface CommonResponseInterface {
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

    /**
     * @SerializedName GSON 자바 객체 필드에 JSON 키를 매핑하는 어노테이션
     * @DTO <Data Transfer Object> 로직을 갖고 있지 않는 순수한 데이터 객체이며 속성과 그 속성에 접근하기 위한 getter, setter 메소드만 가진 클래스를 말합니다
     * @Expose object class의 필드에 @Expose 어노테이션을 추가하면 해당 필드만 json으로 만들어준다
     *          object 중 해당 값이 null일 경우, json으로 만들 필드를 자동 생략해 준다.
     */

//    @SerializedName("get_args") private Args get_args = null;
//    @SerializedName("post_args") private Args post_args = null;
//    public Args Get_args() { return get_args; }
//    public Args Post_args() { return post_args; }
//
//    public class Args {
//        @SerializedName("act") private String param;
//        public String getArgs_get() { return this.param; }
//    }


    @SerializedName("result")      @Expose private Result result = null;//Response result
    @SerializedName("info")        @Expose private Info info;     //Response Info

    public Result getResultInfo()   { return result; } // Json 객체의 Result값을 반환한다.
    public Info getInfoInfo()       { return info; } //Json객체의 Info 값을 반환한다.

    public class Result {
        @SerializedName("action_result")           @Expose private String action_result;           //요청결과 / 실패 : failure , 성공 : success
        @SerializedName("action_success_message")  @Expose private String action_success_message;  //요청성공 메시지 / 실패: null , 성공:접근 성공
        @SerializedName("action_failure_code")     @Expose private String action_failure_code;     //요청 실패코드 / 실패: E0202
        @SerializedName("action_failure_reason")   @Expose private String action_failure_reason;   //요청 실패 이유 / 실패: 전달 인자 값이 올바르지 않습니다

        public String getAction_result()            { return this.action_result; }
        public String getAction_success_message()   { return this.action_success_message; }
        public String getAction_failure_code()      { return this.action_failure_code; }
        public String getAction_failure_reason()    { return this.action_failure_reason; }
    } // Class Result End

    public class Info {
        @SerializedName("name")    @Expose private String name;    //요청 성공시 이름 정보, 실패시 Info 정보는 출력되지 않음.
        @SerializedName("phone")   @Expose private String phone;   //요청 성공시 전화번호 정보, 실패시 Info 정보는 출력되지 않음.

        public String getName()     { return this.name; }
        public String getPhone()    { return this.phone; }
    } // Class Info End


}


