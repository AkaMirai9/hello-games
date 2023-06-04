package com.example.hellogames;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

object HttpServices {
    private const val BASE_URL = "https://education.3ie.fr/android/ressources/api/game/" // Remplace avec l'URL de ton API

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getMyApi(): Api {
        return retrofit.create(Api::class.java)
    }
}
