package com.example.kalkulatormvvm.data



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.kalkulatormvvm.data.model.AppCustomPreset
import com.example.kalkulatormvvm.data.model.UserToken
import com.example.kalkulatormvvm.data.worker.AppCustomPresetWorker

/**
 * The Room database for this app
 */
@Database(entities = [ UserToken::class, AppCustomPreset::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tokenDao(): TokenDao
    abstract fun appCustomDao() : AppCustomDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {

            val DATABASE_NAME = "app-db"
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<AppCustomPresetWorker>().build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
