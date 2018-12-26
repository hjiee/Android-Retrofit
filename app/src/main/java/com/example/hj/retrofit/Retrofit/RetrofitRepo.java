package com.example.hj.retrofit.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetrofitRepo {
    // Response에대한 정보를 담는 변수
    private Result resultInfo;
    private Info infoInfo;

    /**
     * @SerializedName GSON 자바 객체 필드에 JSON 키를 매핑하는 어노테이션
     * @DTO <Data Transfer Object> 로직을 갖고 있지 않는 순수한 데이터 객체이며 속성과 그 속성에 접근하기 위한 getter, setter 메소드만 가진 클래스를 말합니다
     * @Expose object class의 필드에 @Expose 어노테이션을 추가하면 해당 필드만 json으로 만들어준다
     */

    //Response result
    @SerializedName("result")
    @Expose
    private Result result = null;
    public Result getResultInfo() { return result; }

    public class Result {
        //요청결과 / 실패 : failure , 성공 : success
        @SerializedName("action_result")
        @Expose
        private String action_result;
        public String getAction_result() { return this.action_result; }

        //요청성공 메시지 / 실패: null , 성공:접근 성공
        @SerializedName("action_success_message")
        @Expose
        private String action_success_message;
        public String getAction_success_message() { return this.action_success_message; }

        //요청 실패코드 / 실패: E0202
        @SerializedName("action_failure_code")
        @Expose
        private String action_failure_code;
        public String getAction_failure_code() { return this.action_failure_code; }

        //요청 실패 이유 / 실패: 전달 인자 값이 올바르지 않습니다
        @SerializedName("action_failure_reason")
        @Expose
        private String action_failure_reason;
        public String getAction_failure_reason() { return this.action_failure_reason; }

    }

    //Response Info
    @SerializedName("info")
    @Expose
    private Info info;
    public Info getInfoInfo() { return info; }
    public class Info {
        @SerializedName("name")
        @Expose
        private String name;
        public String getName() { return this.name; }

        @SerializedName("phone")
        @Expose
        private String phone;
        public String getPhone() { return this.phone; }
    }
}


