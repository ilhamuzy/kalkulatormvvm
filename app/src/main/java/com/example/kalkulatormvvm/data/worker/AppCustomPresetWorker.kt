package com.example.kalkulatormvvm.data.worker


import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.kalkulatormvvm.data.AppDatabase
import com.example.kalkulatormvvm.data.model.AppCustomPreset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope

class AppCustomPresetWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open("app_custom.json").use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val presetType = object : TypeToken<List<AppCustomPreset>>() {}.type
                    val presetList: List<AppCustomPreset> =
                        Gson().fromJson(jsonReader, presetType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.appCustomDao().insertAll(presetList)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Result.failure()
        }
    }
}
