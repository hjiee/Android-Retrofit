package com.example.hj.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.hj.retrofit.Retrofit.RetrofitClient;
import com.example.hj.retrofit.Retrofit.RetrofitService;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textview;
    //MainActivity 객체
    public static MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        init();

        RetrofitClient retrofitClient = new RetrofitClient();

        //retrofitClient.get();
        retrofitClient.post();

    }
    public void init()
    {
        textview = (TextView) findViewById(R.id.textview);
    }
    public TextView gettextview() { return textview; }
}
