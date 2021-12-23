package com.example.kalkulatormvvm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "custom_preset")
data class AppCustomPreset(
    @PrimaryKey @ColumnInfo(name = "id")  val id: String,
    val preset_name : String,
    val title : String,
    val logo_big : String,
    val logo_thumbnail : String,
    val primary_color : String,
    val secondary_color : String,
    val accent_color : String,
)
