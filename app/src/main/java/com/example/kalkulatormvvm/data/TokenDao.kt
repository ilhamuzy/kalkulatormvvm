package com.example.kalkulatormvvm.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kalkulatormvvm.data.model.UserToken
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the Token class.
 */
@Dao
interface TokenDao {

    @Query("SELECT * FROM token WHERE id = :id")
    fun getToken(id: String): Flow<UserToken>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userToken: UserToken)
}
