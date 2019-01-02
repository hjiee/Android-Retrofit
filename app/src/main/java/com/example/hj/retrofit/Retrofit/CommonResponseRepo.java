package com.example.hj.retrofit.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonResponseRepo {


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

    @SerializedName("result")      @Expose private Result result;//Response result
    @SerializedName("info")        @Expose private Info info;     //Response Info

    public Result getResult()   { return result; } // Json 객체의 Result값을 반환한다.
    public Info getInfo()       { return info; } //Json객체의 Info 값을 반환한다.

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
