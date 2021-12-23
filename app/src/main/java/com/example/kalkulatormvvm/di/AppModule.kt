package com.example.kalkulatormvvm.di



import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.kalkulatormvvm.data.AppCustomDao
import com.example.kalkulatormvvm.data.TokenDao
import com.example.kalkulatormvvm.data.api.ChuckApi
import com.example.kalkulatormvvm.data.repository.DefaultMainRepository
import com.example.kalkulatormvvm.data.repository.MainRepository
import com.example.kalkulatormvvm.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val TIMEOUT_IN_SECONDS = 15L

    @Singleton
    @Provides
    fun provideMainRepository(
        api: ChuckApi,
        tokenDao: TokenDao,
        appCustomDao: AppCustomDao
    ): MainRepository = DefaultMainRepository(api,tokenDao, appCustomDao)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

//    @Provides
//    @Singleton
//    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
//        return OkHttpClient.Builder()
//            .apply {
//
//                if (BuildConfig.DEBUG_BUILD) {
//                    addInterceptor(
//                        ChuckerInterceptor.Builder(context)
//                            .collector(ChuckerCollector(context))
//                            .maxContentLength(250000L)
//                            .redactHeaders(emptySet())
//                            .alwaysReadResponseBody(true)
//                            .build()
//                    )
//                }
//                connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
//                writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
//                readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
//            }
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideChuckApi(okHttpClient: OkHttpClient): ChuckApi = Retrofit.Builder()
//        .client(okHttpClient)
//        .baseUrl(BuildConfig.BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//        .create(ChuckApi::class.java)
}
