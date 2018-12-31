package com.example.hj.retrofit;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hj.retrofit.DTO.UserInfoDTO;
import com.example.hj.retrofit.Retrofit.CommonResponseRepo;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textview;
    private Button btn_get1;
    private Button btn_post1;
    private Button btn_get2;
    private Button btn_post2;
    private EditText edittext;
    public static Context mContext = null;


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //region Class Override Fuction  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        RetrofitThread_set objThread = null;
        switch (nGetViewId){
            case R.id.btn_get1:
                objThread = new RetrofitThread_set(UserSchema.DEF_BASE_URL.concat(UserSchema.DEF_SUB_END_POINT),"GET");
                break;
            case R.id.btn_get2:
                objThread = new RetrofitThread_set(UserSchema.DEF_BASE_URL.concat(UserSchema.DEF_SUB_END_POINT2),"GET");
                break;
            case R.id.btn_post1:
                objThread = new RetrofitThread_set(UserSchema.DEF_BASE_URL.concat(UserSchema.DEF_SUB_END_POINT),"POST");
                break;
            case R.id.btn_post2:
                objThread = new RetrofitThread_set(UserSchema.DEF_BASE_URL.concat(UserSchema.DEF_SUB_END_POINT2),"POST");
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
    public MainActivity getMainActivityInstance()
    {
        if(mContext == null)
            mContext = new MainActivity();
        return (MainActivity)mContext;
    }

    // Activity 초기화 관련 내용을 Override 구현 한다.
    public void reConnectedWidget()
    {
        mContext = this;

        textview = (TextView)findViewById(R.id.textview);
        btn_get1 = (Button)findViewById(R.id.btn_get1);
        btn_post1 = (Button)findViewById(R.id.btn_post1);
        btn_get2 = (Button)findViewById(R.id.btn_get2);
        btn_post2 = (Button)findViewById(R.id.btn_post2);
        edittext = (EditText)findViewById(R.id.edittext);
        btn_get1.setOnClickListener(this);
        btn_post1.setOnClickListener(this);
        btn_get2.setOnClickListener(this);
        btn_post2.setOnClickListener(this);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //region Retrofit Thread
    private class RetrofitThread_set extends Thread {
        String strURL = null;
        String strConnection = null; //Get / Post 연결방식
        public RetrofitThread_set(String strURL,String strConnection)
        {
            this.strURL = strURL;
            this.strConnection = strConnection;
        }
        public void run()
        {
            Retrofit retrofit = UserSchema.getRetrofitInstance(mContext);
            CommonResponseRepo.CommonResponseInterface service = retrofit.create(CommonResponseRepo.CommonResponseInterface.class);
            Call<CommonResponseRepo> comment = null; //만들어둔 interface를 호출하기위한 Call 생성.
            //GET 방식
            if(strConnection.equals("GET")) {
                comment = service.GetComments(strURL, edittext.getText().toString());
            }
            //POST 방식
            else {
                HashMap<String,Object> input = new HashMap<>();
                input.put("act",edittext.getText().toString());
                comment = service.PostComments(strURL,input);
            }
            //enqueue를 통해 Callback에 대해서 정의
            comment.enqueue(new Callback<CommonResponseRepo>() {
                @Override
                public void onResponse(Call<CommonResponseRepo> call, Response<CommonResponseRepo> response) {
                    CommonResponseRepo Repo = response.body();
                    Log.v("Test_Retrofit","Connection to Success");
                    Log.v("Test_Retrofit",response.toString());
                    Log.v("Test_Retrofit",response.headers().toString());
                    Log.v("Test_Retrofit",response.body().toString());
                    setText(Repo);
                }

                @Override
                public void onFailure(Call<CommonResponseRepo> call, Throwable t) {
                    Log.v("Test_Retrofit","Connection to Fail");
                }
            });
        }
    }
    //endregion
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //통신한 결과값을 TextView에 표시한다.
    public void setText(CommonResponseRepo Repo)
    {
        //잘못된 인자가 전달되었을때
        if(Repo.getInfoInfo() == null) {

            textview.setText(Repo.getResultInfo().getAction_result() + "\n"
                    .concat(Repo.getResultInfo().getAction_failure_code() + "\n")
                    .concat(Repo.getResultInfo().getAction_failure_reason() + "\n"));
        }
        else
        {
            //Get방식으로 통신했을때
            if(Repo.getArgs().getAct() != null) {
                textview.setText(Repo.getResultInfo().getAction_result() + "\n"
                        .concat(Repo.getResultInfo().getAction_success_message() + "\n")
                        .concat(Repo.getInfoInfo().getName() + "\n")

                        .concat(Repo.getInfoInfo().getPhone() + "\n")
                        .concat(Repo.getArgs().getAct()));
            }
            //Post방식으로 통신했을때
            else if(Repo.postArgs().getAct() != null) {
                textview.setText(Repo.getResultInfo().getAction_result() + "\n"
                        .concat(Repo.getResultInfo().getAction_success_message() + "\n")
                        .concat(Repo.getInfoInfo().getName() + "\n")
                        .concat(Repo.getInfoInfo().getPhone() + "\n")
                        .concat(Repo.postArgs().getAct()));
            }
        }
    }
}