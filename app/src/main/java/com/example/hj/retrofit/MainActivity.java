package com.example.hj.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.hj.retrofit.Retrofit.RetrofitRepo;
import com.example.hj.retrofit.Retrofit.RetrofitService;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (TextView)findViewById(R.id.textview);

        get();
     //   post();

    }

    public void get()
    {
        Retrofit retrofit_get = new Retrofit.Builder()
                .baseUrl(RetrofitService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //API interface 생성
        RetrofitService retrofitService_get = retrofit_get.create(RetrofitService.class);

        Call<RetrofitRepo> comment = retrofitService_get.getGet("dongju");
        comment.enqueue(new Callback<RetrofitRepo>() {
            @Override
            public void onResponse(Call<RetrofitRepo> call, Response<RetrofitRepo> response) {
//                textview.setText(response.body().getName());
                textview.setText(response.body().getName());
                Log.v("Test","Response Success to Get");
                Log.v("Test",response.toString());
            }

            @Override
            public void onFailure(Call<RetrofitRepo> call, Throwable t) {
                Log.v("Test","Response Fail to Get");
            }
        });

    }

    public void post()
    {
        /**
         * @baseUrl Retrofit 인스터스를 생성
         * @addConverterFactory 객체와 JSON의 변환을 자동으로 하도록 설정
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //API Interface 생성
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        HashMap<String, Object> input = new HashMap<>();
        input.put("김동주", "010-1234-1234");

        Call<RetrofitRepo> call = retrofitService.getInfo(input);
        //요청 수행
        call.enqueue(new Callback<RetrofitRepo>() {
            //성공시
            @Override
            public void onResponse(Call<RetrofitRepo> call, Response<RetrofitRepo> response) {
                RetrofitRepo repo = response.body();
       //         textview.setText(repo.getName());
                textview.setText(repo.toString());
                Log.v("Test","Success to Response");
                Log.v("Test",response.toString());
            }
            //실패시
            @Override
            public void onFailure(Call<RetrofitRepo> call, Throwable t) {
                Log.v("Test","Fail to Response");
            }
        });
    }
}
