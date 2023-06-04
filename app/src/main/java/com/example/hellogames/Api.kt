package com.example.hellogames;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path

interface Api {

    @GET("list.json")
    fun getAllGames(): Call<List<GameModel>>;

    @GET("details{id}.json")
    fun getDetailsGames(@Path("id") id: Int): Call<GameDetails>
}