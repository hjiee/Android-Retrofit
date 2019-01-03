package com.example.hj.retrofit;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hj.retrofit.DTO.UserInfoDTO;
import com.example.hj.retrofit.Retrofit.ResponseRepo1;
import com.example.hj.retrofit.Retrofit.ResponseRepo2;
import com.example.hj.retrofit.Retrofit.Common.RetrofitClient;
import com.google.gson.Gson;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textview;
    private Button btn_get1;
    private Button btn_post1;
    private Button btn_get2;
    private Button btn_post2;
    private EditText edtName;
    private EditText edtPhone;
    private UserInfoDTO userInfoDTO;
    private MessageHandler handler;


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //region Class Override Fuction
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reConnectedWidget();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    //버튼 GET1/GET2 , POST1/POST2
    @Override
    public void onClick(View v) {
        int nGetViewId = v.getId();
        RetrofitThread_Set objThread_set = null;
        RetrofitThread_Param objThread_param = null;
        String name = edtName.getText().toString();
        String phone = edtPhone.getText().toString();

        switch (nGetViewId){
            case R.id.btn_get1:
                objThread_set = new RetrofitThread_Set(ConstantDefine.DEF_SUB_END_POINT,name,phone,ConstantDefine.ORIGIN_METHOD_GET);
                objThread_set.setDaemon(true);
                objThread_set.start();
                break;
            case R.id.btn_get2:
                objThread_param = new RetrofitThread_Param(ConstantDefine.DEF_SUB_END_POINT2,name,phone,ConstantDefine.ORIGIN_METHOD_GET);
                objThread_param.setDaemon(true);
                objThread_param.start();
                break;
            case R.id.btn_post1:
                objThread_set = new RetrofitThread_Set(ConstantDefine.DEF_SUB_END_POINT,name,phone,ConstantDefine.ORIGIN_METHOD_POST);
                objThread_set.setDaemon(true);
                objThread_set.start();
                break;
            case R.id.btn_post2:
                objThread_param = new RetrofitThread_Param(ConstantDefine.DEF_SUB_END_POINT2,name,phone,ConstantDefine.ORIGIN_METHOD_POST);
                objThread_param.setDaemon(true);
                objThread_param.start();
                break;
        }

        /**
         * @setDaemon
         * 다른 일반 쓰레드(데몬 쓰레드가 아닌)의 작업을 돕는 보조적인 역할을 수행하는 쓰레드이다.
         * 일반 쓰레드가 모두 종료되면 데몬 쓰레드는 강제적으로 자동종료된다.
         * 데몬쓰레드가 생성한 쓰레드는 자동으로 데몬 쓰레드가 된다.
         * 예) 가비지컬렉터, 워드프로세서의 자동저장, 화면자동갱신 등
         */



    }
    //endregion Class Override Fuction
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //region Retrofit Handler
    public class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ConstantDefine.HANDLER_MSG_RESPONSEREPO1: //PHP 1
                    Log.v("Test_Retrofit","HANDLER_MSG_RESPONSEREPO1");

                    //잘못된 인자가 전달되었을때
                    if(((ResponseRepo1)msg.obj).getAction_failure_code().equals("failure")) {

                        textview.setText("요청결과 : " + ((ResponseRepo1)msg.obj).getAction_result() + "\n"
                                + ("에러코드 : "+((ResponseRepo1)msg.obj).getAction_failure_code() + "\n")
                                + ("메시지 : "+((ResponseRepo1)msg.obj).getAction_failure_reason() + "\n"));
                    }
                    else
                    {
                        switch(msg.arg1) // Get or Post
                        {
                            case 0: //GET 요청에 대한 결과값
                                textview.setText("요청결과 : "+((ResponseRepo1)msg.obj).getAction_result() + "\n"
                                        + ("메시지 : "+((ResponseRepo1)msg.obj).getAction_success_message() + "\n")
                                        + ("이름 : "+((ResponseRepo1)msg.obj).getName() + "\n")
                                        + ("전화번호 : "+((ResponseRepo1)msg.obj).getPhone()+ "\n")

                                );
                                break;
                            case 1: // POST 요청에 대한 결과값
                                textview.setText("요청결과 : "+((ResponseRepo1)msg.obj).getAction_result() + "\n"
                                        + ("메시지 : "+((ResponseRepo1)msg.obj).getAction_success_message() + "\n")
                                        + ("이름 : "+((ResponseRepo1)msg.obj).getName() + "\n")
                                        + ("전화번호 : "+((ResponseRepo1)msg.obj).getPhone() + "\n")
                                );
                                break;
                        }
                    }

  //                  setText(msg.obj,msg.arg1,ConstantDefine.HANDLER_MSG_CLASS_REPO1);

                    break;
                case ConstantDefine.HANDLER_MSG_RESPONSEREPO2: //PHP 2
                    Log.v("Test_Retrofit","HANDLER_MSG_RESPONSEREPO2");
                    //잘못된 인자가 전달되었을때
                    if(((ResponseRepo2)msg.obj).getAction_failure_code().equals("failure")) {

                        textview.setText("요청결과 : " + ((ResponseRepo2)msg.obj).getAction_result() + "\n"
                                + ("에러코드 : "+((ResponseRepo2)msg.obj).getAction_failure_code() + "\n")
                                + ("메시지 : "+((ResponseRepo2)msg.obj).getAction_failure_reason() + "\n"));
                    }
                    else
                    {
                        switch(msg.arg1) // Get or Post
                        {
                            case 0: //GET 요청에 대한 결과값
                                textview.setText("요청결과 : "+((ResponseRepo2)msg.obj).getAction_result() + "\n"
                                        + ("메시지 : "+((ResponseRepo2)msg.obj).getAction_success_message() + "\n")
                                        + ("이름 : "+((ResponseRepo2)msg.obj).getName() + "\n")
                                        + ("전화번호 : "+((ResponseRepo2)msg.obj).getPhone()+ "\n")

                                );
                                break;
                            case 1: // POST 요청에 대한 결과값
                                textview.setText("요청결과 : "+((ResponseRepo2)msg.obj).getAction_result() + "\n"
                                        + ("메시지 : "+((ResponseRepo2)msg.obj).getAction_success_message() + "\n")
                                        + ("이름 : "+((ResponseRepo2)msg.obj).getName() + "\n")
                                        + ("전화번호 : "+((ResponseRepo2)msg.obj).getPhone() + "\n")
                                );
                                break;
                        }
                    }
  //                  setText(msg.obj,msg.arg1,ConstantDefine.HANDLER_MSG_CLASS_REPO2);
                    break;
                case ConstantDefine.HANDLER_MSG_ERROR: // Connection Fail
                    Toast.makeText(getApplicationContext(),"Network Connection Fail",Toast.LENGTH_LONG).show();
                    break;

            }
            switch(msg.what) {

            }
        }
    }

    //endregion
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //region Retrofit Thread
    private class RetrofitThread_Set extends Thread {
        private String strURL = null;
        private String name = "";
        private String phone = "";
        private int originMethod; //Get / Post 연결방식
        public RetrofitThread_Set(String strURL,String name, String phone, int originMethod)
        {
            this.strURL = ConstantDefine.DEF_BASE_URL+strURL;
            this.name = name;
            this.phone = phone;
            this.originMethod = originMethod;
        }

        public void run()
        {

            Retrofit retrofit = RetrofitClient.getRetrofitInstance(); //Retrofit Singleton 객체를 얻어온다.
            ResponseRepo1.ResponseInterface1 service = retrofit.create(ResponseRepo1.ResponseInterface1.class);
            Call<ResponseRepo1> comment = null; //만들어둔 interface를 호출하기위한 Call 생성.
            HashMap<String,Object> input = new HashMap<>();
            input.put("act",name);
            switch (originMethod)
            {
                case 0: //Get
                    comment = service.GetComments(strURL, input);
                    break;
                case 1: //Post
                    comment = service.PostComments(strURL,input);
                    break;
            }

            //enqueue를 통해 Callback에 대해서 정의
            comment.enqueue(new Callback<ResponseRepo1>() {
                @Override
                public void onResponse(Call<ResponseRepo1> call, Response<ResponseRepo1> response) {
                    ResponseRepo1 Repo = response.body();

                    //Handler로 Response 넘기기.
                    handler = getMessageHandlerInstance();
                    Message msg = handler.obtainMessage();
                    msg.obj = Repo; // Response 객체
                    msg.arg1 = originMethod; // Http통신 방법 0 = Get / 1 = Post
                    msg.what = ConstantDefine.HANDLER_MSG_RESPONSEREPO1; // RetrofitRepo 1 / Resopnse를 받을 클래스 결정

                    handler.sendMessage(msg);

                    Log.v(ConstantDefine.LOG_MSG_TAG_RETROFIT,ConstantDefine.LOG_MSG_SUCCESS);
                    Log.v(ConstantDefine.LOG_MSG_TAG_RETROFIT,response.toString());
                    Log.v(ConstantDefine.LOG_MSG_TAG_RETROFIT,new Gson().toJson(response.body()));
                }

                @Override
                public void onFailure(Call<ResponseRepo1> call, Throwable t) {
                    Log.v(ConstantDefine.LOG_MSG_TAG_RETROFIT,ConstantDefine.LOG_MSG_FAILURE);
//                    MessageHandler handler = new MessageHandler();
                    handler = getMessageHandlerInstance();
                    Message msgerr = handler.obtainMessage();
                    msgerr.what = ConstantDefine.HANDLER_MSG_ERROR;
                }
            });
        }
    }

    //php2 여러개의 파라미터를 넘겨준다.
    private class RetrofitThread_Param extends Thread {
        private String strURL = null;
        private String name = "";
        private String phone = "";
        private int originMethod; //Get / Post 연결방식
        public RetrofitThread_Param(String strURL,String name, String phone, int originMethod)
        {
            this.strURL = ConstantDefine.DEF_BASE_URL+strURL;
            this.name = name;
            this.phone = phone;
            this.originMethod = originMethod;
        }
        public void run()
        {
            Retrofit retrofit = RetrofitClient.getRetrofitInstance();
            ResponseRepo2.ResponseInterface2 service = retrofit.create(ResponseRepo2.ResponseInterface2.class);
            Call<ResponseRepo2> comment = null; //만들어둔 interface를 호출하기위한 Call 생성.
            HashMap<String,Object> input = new HashMap<>();
            input.put("phone",phone);
            input.put("act",name);

            //통신 방식에 대한 구분 Get Post
            switch (originMethod)
            {
                case ConstantDefine.ORIGIN_METHOD_GET:
                    comment = service.GetComments(strURL, input);
                    break;
                case ConstantDefine.ORIGIN_METHOD_POST:
                    comment = service.PostComments(strURL,input);
                    break;
            }


            //enqueue를 통해 Callback에 대해서 정의
            comment.enqueue(new Callback<ResponseRepo2>() {
                @Override
                public void onResponse(Call<ResponseRepo2> call, Response<ResponseRepo2> response) {
                    ResponseRepo2 Repo = response.body();

                    //Handler로 Response 넘기기.

//                    MessageHandler handler = new MessageHandler();
                    handler = getMessageHandlerInstance();
                    Message msg = handler.obtainMessage();
                    msg.obj = Repo; // Response 객체
                    msg.arg1 = originMethod; // Http통신 방법 0 : Get or 1 : Post
                    msg.what = ConstantDefine.HANDLER_MSG_RESPONSEREPO2; // RetrofitRepo 2 / Resopnse를 받을 클래스 결정
                    handler.sendMessage(msg);

                    Log.v(ConstantDefine.LOG_MSG_TAG_RETROFIT,ConstantDefine.LOG_MSG_SUCCESS);
                    Log.v(ConstantDefine.LOG_MSG_TAG_RETROFIT,response.toString());
                    Log.v(ConstantDefine.LOG_MSG_TAG_RETROFIT,new Gson().toJson(response.body()));
                }

                @Override
                public void onFailure(Call<ResponseRepo2> call, Throwable t) {
                    Log.v(ConstantDefine.LOG_MSG_TAG_RETROFIT,ConstantDefine.LOG_MSG_FAILURE);
//                    MessageHandler handler = new MessageHandler();
                    handler = getMessageHandlerInstance();
                    Message msgerr = handler.obtainMessage();
                    msgerr.what = ConstantDefine.HANDLER_MSG_ERROR;
                }
            });
        }
    }
    //endregion
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //MessageHandler Singleton Instance
    public MessageHandler getMessageHandlerInstance() {
        if(handler == null) {
            handler = new MessageHandler();
        }
        return handler;
    }

    // Activity 초기화 관련 내용을 Override 구현 한다.
    public void reConnectedWidget()
    {
        textview = (TextView)findViewById(R.id.textview);
        btn_get1 = (Button)findViewById(R.id.btn_get1);
        btn_post1 = (Button)findViewById(R.id.btn_post1);
        btn_get2 = (Button)findViewById(R.id.btn_get2);
        btn_post2 = (Button)findViewById(R.id.btn_post2);
        edtName = (EditText)findViewById(R.id.edtName);
        edtPhone = (EditText)findViewById(R.id.edtPhone);
        btn_get1.setOnClickListener(this);
        btn_post1.setOnClickListener(this);
        btn_get2.setOnClickListener(this);
        btn_post2.setOnClickListener(this);
    }
}