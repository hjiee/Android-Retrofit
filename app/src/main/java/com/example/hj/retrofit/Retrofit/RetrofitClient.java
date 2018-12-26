package com.example.hj.retrofit.Retrofit;

import android.util.Log;
import android.widget.TextView;

import com.example.hj.retrofit.MainActivity;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.hj.retrofit.MainActivity.mainActivity;

public class RetrofitClient {
    private Retrofit retrofit;
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //"https://2018-anyfood-dev.workservice.kr:773/"
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @baseUrl Retrofit 인스터스를 생성
     * @addConverterFactory 객체와 JSON의 변환을 자동으로 하도록 설정
     */
    public RetrofitClient() {
        getRetrofitClient();
    }
    //Retrofit 객체 Singleton 적용
    public Retrofit getRetrofitClient()
    {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(RetrofitService.url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public void get() {
        //API interface 생성
        RetrofitService retrofitService_get = retrofit.create(RetrofitService.class);

        Call<RetrofitRepo> comment = retrofitService_get.getGet("dongju");
        comment.enqueue(new Callback<RetrofitRepo>() {
            @Override
            public void onResponse(Call<RetrofitRepo> call, Response<RetrofitRepo> response) {
                RetrofitRepo Repo = response.body();

                Log.v("Test_Retrofit", "Response Success to Get");
                Log.v("Test_Retrofit", "Response Body = ".concat(response.body().toString()));
                Log.v("Test_Retrofit", response.toString());

                if(Repo.getInfoInfo() == null) {
                    mainActivity.gettextview().setText(Repo.getResultInfo().getAction_result() + "\n"
                            .concat(Repo.getResultInfo().getAction_failure_code() + "\n")
                            .concat(Repo.getResultInfo().getAction_failure_reason() + "\n"));
                }
                else
                {
                    mainActivity.gettextview().setText(Repo.getResultInfo().getAction_result() + "\n"
                            .concat(Repo.getResultInfo().getAction_success_message() + "\n")
                            .concat(Repo.getInfoInfo().getName() + "\n")
                            .concat(Repo.getInfoInfo().getPhone()));
                }
            }

            @Override
            public void onFailure(Call<RetrofitRepo> call, Throwable t) {
                Log.v("Test_Retrofit", "Response Fail to Get");
            }
        });
    }

    public void post()
    {
        //API Interface 생성
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        HashMap<String, Object> input = new HashMap<>();
      //  input.put("김동주", "010-1234-1234");

        Call<RetrofitRepo> call = retrofitService.getComments(input);
        //요청 수행
        call.enqueue(new Callback<RetrofitRepo>() {
            //성공시
            @Override
                public void onResponse(Call<RetrofitRepo> call, Response<RetrofitRepo> respon) {
                    RetrofitRepo Repo = respon.body();
                    //         textview.setText(repo.getName());
                    //        textview.setText(repo.toString());
                    Log.v("Test_Retrofit","Success to Response");
                    Log.v("Test_Retrofit",respon.toString());
                    Log.v("Test_Retrofit",Repo.getResultInfo().getAction_result());

                mainActivity.gettextview().setText(Repo.getResultInfo().getAction_result() + "\n"
                        .concat(Repo.getResultInfo().getAction_success_message() + "\n")
                        .concat(Repo.getInfoInfo().getName() + "\n")
                        .concat(Repo.getInfoInfo().getPhone()));
            }
            //실패시
            @Override
            public void onFailure(Call<RetrofitRepo> call, Throwable t) {
                Log.v("Test_Retrofit","Fail to Response");
            }
        });
    }
}
