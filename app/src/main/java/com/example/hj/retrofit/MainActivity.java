package com.example.hj.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    public static final String url = "https://2018-anyfood-dev.workservice.kr:773/kr/test/";
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (TextView)findViewById(R.id.textview);

        post();

    }


    public void post()
    {
        /**
         * @baseUrl Retrofit 인스터스를 생성
         * @addConverterFactory 객체와 JSON의 변환을 자동으로 하도록 설정
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //API Interface 생성
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        HashMap<String, Object> input = new HashMap<>();
        input.put("name", "dongju");

        Call<RetrofitRepo> call = retrofitService.getInfo(input);
        //요청 수행
        call.enqueue(new Callback<RetrofitRepo>() {
            //성공시
            @Override
            public void onResponse(Call<RetrofitRepo> call, Response<RetrofitRepo> response) {
       //         RetrofitRepo repo = response.body();
       //         textview.setText(repo.getName());
                textview.setText(response.body().getName());
            }
            //실패시
            @Override
            public void onFailure(Call<RetrofitRepo> call, Throwable t) {

            }
        });
    }
}
