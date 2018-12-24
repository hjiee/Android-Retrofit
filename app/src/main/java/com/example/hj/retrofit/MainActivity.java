package com.example.hj.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hj.retrofit.Retrofit.RetrofitRepo;
import com.example.hj.retrofit.Retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    public static final String url = "https://2018-anyfood-dev.workservice.kr:773/kr/test/test.php/";
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (TextView)findViewById(R.id.textview);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<RetrofitRepo> call = retrofitService.getPost("post");
//        call.enqueue(new Callback<RetrofitRepo>() {
//            @Override
//            public void onResponse(Call<RetrofitRepo> call, Response<RetrofitRepo> response) {
//                RetrofitRepo repo = response.body();
//                textview.setText(repo.getOutput());
//            }
//
//            @Override
//            public void onFailure(Call<RetrofitRepo> call, Throwable t) {
//
//            }
//        });
    }
}
