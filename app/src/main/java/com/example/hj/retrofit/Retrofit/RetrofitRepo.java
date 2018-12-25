package com.example.hj.retrofit.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetrofitRepo {

//    @SerializedName("respons") //reponse 시 Fail
//    @Expose
//    private String response;
//    public String getresponse() { return this.response; }
    //    @SerializedName("info")
//    @Expose
//    private String info;

//    public String getinfo() { return this.info; }


    /**
     * @SerializedName GSON 자바 객체 필드에 JSON 키를 매핑하는 어노테이션
     */
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("action_result")
    @Expose
    private String action_result;

    @SerializedName("action_success_message")
    @Expose
    private String action_success_message;

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("body")
    @Expose
    private String body;

    public String getuserId() { return this.userId; }
    public String getid() { return this.id; }
    public String gettitle() { return this.title; }
    public String getbody() { return this.body; }
//
//    @SerializedName("info")
//    @Expose
//    private String info;
    
    public String getName() { return this.name; }
    public String getPhone() { return this.phone; }
    public String getAction_result() { return this.action_result; }
    public String getAction_success_message() { return this.action_success_message; }

//    	"response": {
//        "action_result": "success",
//                "action_failure_code": "",
//                "action_failure_reason": "",
//                "action_success_message": "접근 성공"
//    },
//            "info": {
//        "name": "김동주",
//                "phone": "010-1234-1234"
//    }

}


