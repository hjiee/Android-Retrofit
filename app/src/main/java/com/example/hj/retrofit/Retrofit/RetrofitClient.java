package com.example.hj.retrofit.Retrofit;

import android.util.Log;

import com.example.hj.retrofit.ConstantDefine;
import com.example.hj.retrofit.MainActivity;

import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private Retrofit retrofit;
    private RetrofitServiceInterface retrofitServiceInterface;
    private RetrofitClient retrofitClient;



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //"https://2018-anyfood-dev.workservice.kr:773/"
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @baseUrl Retrofit 인스터스를 생성
     * @addConverterFactory 객체와 JSON의 변환을 자동으로 하도록 설정
     */
    public RetrofitClient() {
        //API instance 생성
//        retrofit = new Retrofit.Builder()
//                .baseUrl(ConstantDefine.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create()) //request / response json을 자동으로 conver해준다.
//                .build();


    }
    public Retrofit getRetrofitIntance()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(ConstantDefine.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //request / response json을 자동으로 conver해준다.
                .build();
        retrofitServiceInterface = retrofit.create(RetrofitServiceInterface.class);
        return retrofit;
    }

    public void get(String path) {


        Call<RetrofitRepo> comment = retrofitServiceInterface.GetComments(path,((MainActivity)MainActivity.mContext).getMainActivityInstance().getEdittext());
        comment.enqueue(new Callback<RetrofitRepo>() {
            /**
             * @param call
             * @param response 통신 완료후 받은 객체
             *                 response.body()는 resposejson이 deserialize된 responseJson 객체.
             */
            @Override
            public void onResponse(Call<RetrofitRepo> call, Response<RetrofitRepo> response) {
                Log.v("Test_Retrofit", "Response Success to GET");
                Log.v("Test_Retrofit", response.toString());
                RetrofitRepo Repo = response.body();
                setText(Repo);
            }

            @Override
            public void onFailure(Call<RetrofitRepo> call, Throwable t) {
                Log.v("Test_Retrofit", "Response Fail to GET");
            }
        });
    }

    public void post(String path)
    {
        //POST parameter
        HashMap<String, Object> input = new HashMap<>();
        input.put("act", ((MainActivity)MainActivity.mContext).getMainActivityInstance().getEdittext());
        Call<RetrofitRepo> call = retrofitServiceInterface.PostComments(path,input);
        //요청 수행
        call.enqueue(new Callback<RetrofitRepo>() {
            //성공시
            @Override
                public void onResponse(Call<RetrofitRepo> call, Response<RetrofitRepo> respon) {
                    Log.v("Test_Retrofit","Response Success to POST");
                    Log.v("Test_Retrofit",respon.toString());
                    RetrofitRepo Repo = respon.body();
                    setText(Repo);
            }
            //실패시
            @Override
            public void onFailure(Call<RetrofitRepo> call, Throwable t) {
                Log.v("Test_Retrofit", "Response Fail to POST");
            }
        });


    }
    public void setText(RetrofitRepo Repo)
    {
        if(Repo.getInfoInfo() == null) {
            ((MainActivity)MainActivity.mContext).getMainActivityInstance().gettextview().setText(Repo.getResultInfo().getAction_result() + "\n"
                    .concat(Repo.getResultInfo().getAction_failure_code() + "\n")
                    .concat(Repo.getResultInfo().getAction_failure_reason() + "\n"));
        }
        else
        {
            ((MainActivity)MainActivity.mContext).getMainActivityInstance().gettextview().setText(Repo.getResultInfo().getAction_result() + "\n"
                    .concat(Repo.getResultInfo().getAction_success_message() + "\n")
                    .concat(Repo.getInfoInfo().getName() + "\n")
                    .concat(Repo.getInfoInfo().getPhone()));
        }
    }
}
