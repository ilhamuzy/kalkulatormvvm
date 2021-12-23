package com.example.kalkulatormvvm.data.repository

import com.example.kalkulatormvvm.data.model.ChuckResponse
import com.example.kalkulatormvvm.utils.Resource


interface MainRepository {

    suspend fun getChuck(): Resource<ChuckResponse>

    suspend fun testDatabase()

    suspend fun testRead()
}
