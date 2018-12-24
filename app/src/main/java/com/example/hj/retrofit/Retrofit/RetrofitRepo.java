package com.example.hj.retrofit.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetrofitRepo {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("phone")
    @Expose
    private String phone;
    
    public String getName()
    {
        return name;
    }
    public String getPhone()
    {
        return phone;
    }
}
