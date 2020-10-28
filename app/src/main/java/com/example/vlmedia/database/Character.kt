package com.example.vlmedia.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CHARACTER")
data class Character(
    @JvmField @PrimaryKey var id: String,
    @JvmField @ColumnInfo(name = "NAME") var name: String,
    @JvmField @ColumnInfo(name = "IMAGE") var image: String,
    @JvmField @ColumnInfo(name = "STATUS") var status: String,
    @JvmField @ColumnInfo(name = "LOCATION") var location: String
)