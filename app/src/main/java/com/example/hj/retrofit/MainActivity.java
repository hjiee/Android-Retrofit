package com.example.hj.retrofit;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hj.retrofit.Retrofit.CommonResponseRepo;

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
    private EditText edittext;
    public Context mContext = null;
    private RetrofitThread_set retrofitThread_set;

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
                objThread = new RetrofitThread_set(UserSchema.DEF_SUB_END_POINT,"GET");
                break;
            case R.id.btn_get2:
                objThread = new RetrofitThread_set(UserSchema.DEF_SUB_END_POINT2,"GET");
                break;
            case R.id.btn_post1:
                objThread = new RetrofitThread_set(UserSchema.DEF_SUB_END_POINT,"POST");
                break;
            case R.id.btn_post2:
                objThread = new RetrofitThread_set(UserSchema.DEF_SUB_END_POINT2,"POST");
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

    /////////////////////////////////////////////////////////////////////////////////////////////////
    //region Get Instance
    public MainActivity getMainActivityInstance()
    {
        if(mContext == null)
            mContext = new MainActivity();
        return (MainActivity)mContext;
    }
    //endregion
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //region Retrofit Thread
    private class RetrofitThread_set extends Thread {
        private String strURL = null;
        private String strConnection = null; //Get / Post 연결방식
        public RetrofitThread_set(String strURL,String strConnection)
        {
            this.strURL = UserSchema.DEF_BASE_URL+strURL;
            this.strConnection = strConnection;
        }
        public void run()
        {
            Retrofit retrofit = UserSchema.getRetrofitInstance(mContext);
            CommonResponseRepo.CommonResponseInterface service = retrofit.create(CommonResponseRepo.CommonResponseInterface.class);
            Call<CommonResponseRepo> comment = null; //만들어둔 interface를 호출하기위한 Call 생성.

            switch (strConnection)
            {
                case "GET":
                    comment = service.GetComments(strURL, edittext.getText().toString());
                    break;
                case "POST":
                    HashMap<String,Object> input = new HashMap<>();
                    input.put("act",edittext.getText().toString());
                    comment = service.PostComments(strURL,input);
                    break;
            }

            //enqueue를 통해 Callback에 대해서 정의
            comment.enqueue(new Callback<CommonResponseRepo>() {
                @Override
                public void onResponse(Call<CommonResponseRepo> call, Response<CommonResponseRepo> response) {
                    CommonResponseRepo Repo = response.body();
                    Log.v("Test_Retrofit","Connection to Success");
                    setText(Repo,strConnection);
                }

                @Override
                public void onFailure(Call<CommonResponseRepo> call, Throwable t) {
                    Log.v("Test_Retrofit","Connection to Fail");
                    Toast.makeText(mContext,"Network Connection to Fail",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    //endregion
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //통신한 결과값을 TextView에 표시한다.
    public void setText(CommonResponseRepo Repo,String strConnection)
    {
        //잘못된 인자가 전달되었을때
        if(Repo.getResultInfo().getAction_result().equals("failure")) {

            textview.setText("요청결과 : " + Repo.getResultInfo().getAction_result() + "\n"
                    + ("에러코드 : "+Repo.getResultInfo().getAction_failure_code() + "\n")
                    + ("메시지 : "+Repo.getResultInfo().getAction_failure_reason() + "\n"));
        }
        else
        {
            switch(strConnection)
            {
                case "GET": //GET 요청에 대한 결과값
                    textview.setText("요청결과 : "+Repo.getResultInfo().getAction_result() + "\n"
                            + ("메시지 : "+Repo.getResultInfo().getAction_success_message() + "\n")
                            + ("이름 : "+Repo.getInfoInfo().getName() + "\n")
                            + ("전화번호 : "+Repo.getInfoInfo().getPhone() + "\n")

                    );
                    break;
                case "POST": // POST 요청에 대한 결과값
                    textview.setText("요청결과 : "+Repo.getResultInfo().getAction_result() + "\n"
                            + ("메시지 : "+Repo.getResultInfo().getAction_success_message() + "\n")
                            + ("이름 : "+Repo.getInfoInfo().getName() + "\n")
                            + ("전화번호 : "+Repo.getInfoInfo().getPhone() + "\n")
                    );
                    break;
            }
        }
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
}