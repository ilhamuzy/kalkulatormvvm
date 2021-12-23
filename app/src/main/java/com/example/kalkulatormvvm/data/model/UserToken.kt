package com.example.kalkulatormvvm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "token")
data class UserToken(
    @PrimaryKey @ColumnInfo(name = "id")  val id: String,
    val jwt: String,
    val time: String,
)
