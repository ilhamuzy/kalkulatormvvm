package com.example.kalkulatormvvm.data.api


import com.example.kalkulatormvvm.data.model.ChuckResponse
import retrofit2.Response
import retrofit2.http.GET

interface ChuckApi {
	
    @GET("/jokes/random")
    suspend fun getChuck(
    ): Response<ChuckResponse>

}
