package com.example.hj.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.hj.retrofit.Retrofit.RetrofitRepo;
import com.example.hj.retrofit.Retrofit.RetrofitService;

import java.util.HashMap;
import java.util.List;

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

   //     get();
     //   post();

     //  apiGet();
        apiPost();

    }
    public void apiPost()
    {
        /**
         * @baseUrl Retrofit 인스터스를 생성
         * @addConverterFactory 객체와 JSON의 변환을 자동으로 하도록 설정
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.API_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HashMap<String, Object> input = new HashMap<>();
        input.put("1","1");
        //API Interface 생성
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<RetrofitRepo> call = retrofitService.postFirst(input);
        // 앞서만든 요청을 수행
        call.enqueue(new Callback<RetrofitRepo>() {
            @Override
            // 성공시
            public void onResponse(Call<RetrofitRepo> call, Response<RetrofitRepo> response) {
                Log.v("Test_retrofit","Success to Response");
                RetrofitRepo contributors = response.body(); // 통신후 데이터 body를 RetrofitRepo 타입의 List객체에 담아 미리선언되어있는 메소드로 부터 얻을수 있다.
                Log.v("Test_retrofit",response.body().toString());
                Log.v("Test_retrofit",response.toString());
                textview.setText((response.toString()));
                // 받아온 리스트를 순회하면서
       //         for (RetrofitRepo contributor : contributors) {
                    // 카테고리 userID , id , body , title
                    // 텍스트 뷰에 id 카테고리 정보를 붙임
                    textview.append(contributors.getbody()+"\n");
                    Log.v("Test_retrofit",contributors.getid());

      //          }
            }
            @Override
            // 실패시
            public void onFailure(Call<RetrofitRepo> call, Throwable t) {
                Log.v("Test","Fail to Response");
            }
        });
    }
    public void apiGet()
    {
        /**
         * @baseUrl Retrofit 인스터스를 생성
         * @addConverterFactory 객체와 JSON의 변환을 자동으로 하도록 설정
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.API_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //API Interface 생성
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<List<RetrofitRepo>> call = retrofitService.getSecond("1"); //userid 1 ~ .... / 각 useId 별 10개의 데이터
        // 앞서만든 요청을 수행
        call.enqueue(new Callback<List<RetrofitRepo>>() {
            @Override
            // 성공시
            public void onResponse(Call<List<RetrofitRepo>> call, Response<List<RetrofitRepo>> response) {
                Log.v("Test_retrofit","Success to Response");
                List<RetrofitRepo> contributors = response.body(); // 통신후 데이터 body를 RetrofitRepo 타입의 List객체에 담아 미리선언되어있는 메소드로 부터 얻을수 있다.
                Log.v("Test_retrofit",response.body().toString());
                Log.v("Test_retrofit",response.toString());
                textview.setText((response.toString()));
                // 받아온 리스트를 순회하면서
                for (RetrofitRepo contributor : contributors) {
                    // 카테고리 userID , id , body , title
                    // 텍스트 뷰에 id 카테고리 정보를 붙임
                    textview.append(contributor.getbody()+"\n");
                    Log.v("Test_retrofit",contributor.getid());
                }
            }
            @Override
            // 실패시
            public void onFailure(Call<List<RetrofitRepo>> call, Throwable t) {
                Log.v("Test","Fail to Response");
            }
        });
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

        Call<RetrofitRepo> call = retrofitService.getComments(input);
        //요청 수행
        call.enqueue(new Callback<RetrofitRepo>() {
            //성공시
            @Override
            public void onResponse(Call<RetrofitRepo> call, Response<RetrofitRepo> respon) {
                RetrofitRepo repo = respon.body();
       //         textview.setText(repo.getName());
        //        textview.setText(repo.toString());
                Log.v("Test","Success to Response");
                Log.v("Test",respon.toString());
       //         Log.v("Test",response.body().toString());


            }
            //실패시
            @Override
            public void onFailure(Call<RetrofitRepo> call, Throwable t) {
                Log.v("Test","Fail to Response");
            }
        });
    }
}
