package com.example.kalkulatormvvm.data



import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kalkulatormvvm.data.model.AppCustomPreset
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the AppCustomPreset class.
 */
@Dao
interface AppCustomDao {

    @Query("SELECT * FROM custom_preset ORDER BY preset_name")
    fun getAppCustomPresets(): Flow<List<AppCustomPreset>>

    @Query("SELECT * FROM custom_preset WHERE id = :id")
    fun getAppCustomPresetById(id: String): Flow<AppCustomPreset>

    @Query("SELECT * FROM custom_preset WHERE preset_name = :presetName")
    fun getAppCustomPresetByName(presetName: String): Flow<AppCustomPreset>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<AppCustomPreset>)
}
