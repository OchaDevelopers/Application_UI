package com.example.mrhjs.ocha;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface  NetworkService {

    @Headers("Content-Type:application/json")
    @POST("/")
    Call<Data> newContent(@Body Data content);
}