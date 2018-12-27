package com.example.hj.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hj.retrofit.Retrofit.RetrofitClient;
import com.example.hj.retrofit.Retrofit.RetrofitDTO;
import com.example.hj.retrofit.Retrofit.RetrofitServiceInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textview;
    private Button btn_get1;
    private Button btn_post1;
    private Button btn_get2;
    private Button btn_post2;
    private EditText edittext;
    private RetrofitClient retrofitClient;
    //MainActivity 객체
    public static MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        init();

        retrofitClient = new RetrofitClient();
    }
    public TextView gettextview() { return textview; }
    public String getEdittext() { return edittext.getText().toString(); }
    public void init()
    {
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

    //버튼 GET1/GET2 , POST1/POST2
    @Override
    public void onClick(View v) {
        int nGetViewId = v.getId();
        switch (nGetViewId){
            case R.id.btn_get1:
                retrofitClient.get(ConstantDefine.FIRST_END_POINT);
                break;
            case R.id.btn_get2:
                retrofitClient.get(ConstantDefine.SECOND_END_POINT);
                break;
            case R.id.btn_post1:
                retrofitClient.post(ConstantDefine.FIRST_END_POINT);
                break;
            case R.id.btn_post2:
                retrofitClient.post(ConstantDefine.SECOND_END_POINT);
                break;
        }
    }
}
