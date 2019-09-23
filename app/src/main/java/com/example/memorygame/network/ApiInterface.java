package com.example.memorygame.network;

import com.example.memorygame.models.MemoryGameResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("products.json?")
    Call<MemoryGameResponse> getShopifyMemoryGameResponse(@Query("page") String page, @Query("access_token") String token);
}
