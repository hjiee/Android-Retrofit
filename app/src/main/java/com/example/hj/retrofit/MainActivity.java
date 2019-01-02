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
import com.google.gson.Gson;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textview;
    private Button btn_get1;
    private Button btn_post1;
    private Button btn_get2;
    private Button btn_post2;
    private EditText edtName;
    private EditText edtPhone;
    private UserInfoDTO userInfoDTO;


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //region Class Override Fuction  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        RetrofitThread_Set objThread = null;
        String name = edtName.getText().toString();
        String phone = edtPhone.getText().toString();

        switch (nGetViewId){
            case R.id.btn_get1:
                objThread = new RetrofitThread_Set(UserSchema.DEF_SUB_END_POINT,name,phone,UserSchema.originMethodGet);
                break;
            case R.id.btn_get2:
                objThread = new RetrofitThread_Set(UserSchema.DEF_SUB_END_POINT2,name,phone,UserSchema.originMethodGet);
                break;
            case R.id.btn_post1:
                objThread = new RetrofitThread_Set(UserSchema.DEF_SUB_END_POINT,name,phone,UserSchema.originMethodPost);
                break;
            case R.id.btn_post2:
                objThread = new RetrofitThread_Set(UserSchema.DEF_SUB_END_POINT2,name,phone,UserSchema.originMethodPost);
                break;
        }

        /**
         * @setDaemon
         * 다른 일반 쓰레드(데몬 쓰레드가 아닌)의 작업을 돕는 보조적인 역할을 수행하는 쓰레드이다.
         * 일반 쓰레드가 모두 종료되면 데몬 쓰레드는 강제적으로 자동종료된다.
         * 데몬쓰레드가 생성한 쓰레드는 자동으로 데몬 쓰레드가 된다.
         * 예) 가비지컬렉터, 워드프로세서의 자동저장, 화면자동갱신 등
         */

        objThread.setDaemon(true);
        objThread.start();


    }
    //endregion Class Override Fuction
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public class MessageHanldler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setText((ResponseRepo1)msg.obj,msg.arg1);
            switch(msg.what) {

            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //region Retrofit Thread
    private class RetrofitThread_Set extends Thread {
        private String strURL = null;
        private String name = "";
        private String phone = "";
        private int originMethod; //Get / Post 연결방식
        public RetrofitThread_Set(String strURL,String name, String phone, int originMethod)
        {
            this.strURL = UserSchema.DEF_BASE_URL+strURL;
            this.name = name;
            this.phone = phone;
            this.originMethod = originMethod;

        }
        public void run()
        {
            Retrofit retrofit = UserSchema.getRetrofitInstance();
            ResponseRepo1.ResponseInterface1 service = retrofit.create(ResponseRepo1.ResponseInterface1.class);
            Call<ResponseRepo1> comment = null; //만들어둔 interface를 호출하기위한 Call 생성.
            HashMap<String,Object> input = new HashMap<>();
            input.put("act",name);
            input.put("phone",phone);
            switch (originMethod)
            {
                case 0:
                    comment = service.GetComments(strURL, name);
                    break;
                case 1:

                    comment = service.PostComments(strURL,input);
                    break;
            }

            //enqueue를 통해 Callback에 대해서 정의
            comment.enqueue(new Callback<ResponseRepo1>() {
                @Override
                public void onResponse(Call<ResponseRepo1> call, Response<ResponseRepo1> response) {
                    ResponseRepo1 Repo = response.body();

                    //Handler로 Response 넘기기.
                    MessageHanldler handler = new MessageHanldler();
                    Message msg = handler.obtainMessage();
                    msg.obj = Repo; // Response 객체
                    msg.arg1 = originMethod; // Http통신 방법 0 : Get or 1 : Post

                    handler.sendMessage(msg);

                    Log.v("Test_Retrofit","Connection Success");
                    Log.v("Test_Retrofit",response.toString());
                    Log.v("Test_Retrofit",new Gson().toJson(response.body()));
                }

                @Override
                public void onFailure(Call<ResponseRepo1> call, Throwable t) {
                    Log.v("Test_Retrofit","Connection Fail");
                    Toast.makeText(getApplicationContext(),"Network Connection Fail",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    //endregion
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //통신한 결과값을 TextView에 표시한다.
    public void setText(ResponseRepo1 Repo, int originMethod)
    {

        //잘못된 인자가 전달되었을때
        if(Repo.getAction_result().equals("failure")) {

            textview.setText("요청결과 : " + Repo.getAction_result() + "\n"
                    + ("에러코드 : "+Repo.getAction_failure_code() + "\n")
                    + ("메시지 : "+Repo.getAction_failure_reason() + "\n"));
        }
        else
        {
            switch(originMethod) // Get or Post
            {
                case 0: //GET 요청에 대한 결과값
                    textview.setText("요청결과 : "+Repo.getAction_result() + "\n"
                            + ("메시지 : "+Repo.getAction_success_message() + "\n")
                            + ("이름 : "+Repo.getName() + "\n")
                            + ("전화번호 : "+Repo.getPhone() + "\n")

                    );
                    break;
                case 1: // POST 요청에 대한 결과값
                    textview.setText("요청결과 : "+Repo.getAction_result() + "\n"
                            + ("메시지 : "+Repo.getAction_success_message() + "\n")
                            + ("이름 : "+Repo.getName() + "\n")
                            + ("전화번호 : "+Repo.getPhone() + "\n")
                    );
                    break;
            }
        }
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