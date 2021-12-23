package com.example.kalkulatormvvm.di

import android.content.Context
import com.example.kalkulatormvvm.data.AppCustomDao
import com.example.kalkulatormvvm.data.AppDatabase
import com.example.kalkulatormvvm.data.TokenDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideTokenDao(appDatabase: AppDatabase): TokenDao {
        return appDatabase.tokenDao()
    }

    @Provides
    fun provideAppCustomDao(appDatabase: AppDatabase): AppCustomDao {
        return appDatabase.appCustomDao()
    }
}
