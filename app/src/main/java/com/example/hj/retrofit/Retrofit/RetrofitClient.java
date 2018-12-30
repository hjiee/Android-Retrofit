package com.example.hj.retrofit.Retrofit;

import android.util.Log;

import com.example.hj.retrofit.ConstantDefine;
import com.example.hj.retrofit.DTO.UserInfoDTO;
import com.example.hj.retrofit.MainActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //"https://2018-anyfood-dev.workservice.kr:773/"
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @baseUrl Retrofit 인스터스를 생성
     * @addConverterFactory 객체와 JSON의 변환을 자동으로 하도록 설정
     */
    public RetrofitClient() {


    }

    public void get(String path) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantDefine.DEF_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //request / response json을 자동으로 conver해준다.
                .build();
        CommonResponseRepo.CommonResponseInterface service = retrofit.create((CommonResponseRepo.CommonResponseInterface.class));

        Call<CommonResponseRepo> comment = service.GetComments(path,((MainActivity)MainActivity.mContext).getMainActivityInstance().getEdittext());
        comment.enqueue(new Callback<CommonResponseRepo>() {
            /**
             * @param call
             * @param response 통신 완료후 받은 객체
             *                 response.body()는 resposejson이 deserialize된 responseJson 객체.
             */
            @Override
            public void onResponse(Call<CommonResponseRepo> call, Response<CommonResponseRepo> response) {
                Log.v("Test_Retrofit", "Response Success to GET");
                Log.v("Test_Retrofit", response.toString());
                CommonResponseRepo Repo = response.body();
                UserInfoDTO userInfoDTO = new UserInfoDTO();


       //         userInfoDTO.UserName = Repo.getInfoInfo().getName();
       //         userInfoDTO.UserPhone = Repo.getInfoInfo().getPhone();

                setText(Repo);
            }

            @Override
            public void onFailure(Call<CommonResponseRepo> call, Throwable t) {
                Log.v("Test_Retrofit", "Response Fail to GET");
            }
        });
    }

    public void post(String path)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantDefine.DEF_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //request / response json을 자동으로 conver해준다.
                .build();
        CommonResponseRepo.CommonResponseInterface service = retrofit.create((CommonResponseRepo.CommonResponseInterface.class));

        //POST parameter
        HashMap<String, Object> input = new HashMap<>();
        input.put("act", ((MainActivity)MainActivity.mContext).getMainActivityInstance().getEdittext());
        Call<CommonResponseRepo> call = service.PostComments(path,input);
        //요청 수행
        call.enqueue(new Callback<CommonResponseRepo>() {
            //성공시
            @Override
                public void onResponse(Call<CommonResponseRepo> call, Response<CommonResponseRepo> respon) {
                    Log.v("Test_Retrofit","Response Success to POST");
                    Log.v("Test_Retrofit",respon.toString());
                    CommonResponseRepo Repo = respon.body();
                    setText(Repo);
            }
            //실패시
            @Override
            public void onFailure(Call<CommonResponseRepo> call, Throwable t) {
                Log.v("Test_Retrofit", "Response Fail to POST");
            }
        });


    }
    public void setText(CommonResponseRepo Repo)
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
