package com.example.kalkulatormvvm.data.repository

import com.example.kalkulatormvvm.data.AppCustomDao
import com.example.kalkulatormvvm.data.TokenDao
import com.example.kalkulatormvvm.data.api.ChuckApi
import com.example.kalkulatormvvm.data.model.ChuckResponse
import com.example.kalkulatormvvm.data.model.UserToken
import com.example.kalkulatormvvm.utils.Resource
import com.google.gson.Gson


import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DefaultMainRepository @Inject constructor(
    private val api: ChuckApi,
    private val tokenDao: TokenDao,
    private val appCustomDao: AppCustomDao,
) : MainRepository {

    override suspend fun getChuck(): Resource<ChuckResponse> {
        return try {
            val response = api.getChuck()
            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch(e: Exception) {
            Resource.Error(e.message ?: "An error occured")
        }
    }

    override suspend fun testDatabase() {
        tokenDao.insertAll(UserToken("1","test","test"))
    }

    override suspend fun testRead(){
        tokenDao.getToken("1").collect {

        }
    }
}
