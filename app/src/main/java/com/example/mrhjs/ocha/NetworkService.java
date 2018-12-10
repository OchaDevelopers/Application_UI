package com.example.mrhjs.ocha;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface  NetworkService {

    @Headers("Content-Type:application/json")
    @POST("/memberReg")
    Call<Signup_data> newContent(@Body Signup_data content);
    @Headers("Content-Type:application/json")
    @POST("/findPassword")
    Call<Find_Data> newContentfind(@Body Find_Data content);
    @Headers("Content-Type:application/json")
    @POST("/login")
    Call<Signin_data> newContentin(@Body Signin_data content);
    @Headers("Content-Type:application/json")
    @POST("/recommend")
    Call<Recommand_data> newContentre(@Body Recommand_data content);
}