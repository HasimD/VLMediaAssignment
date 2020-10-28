package com.example.vlmedia.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Character::class], version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun charDao(): CharDao

    companion object {
        private var appRoomDatabase: AppRoomDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppRoomDatabase {
            if (appRoomDatabase == null) {
                appRoomDatabase = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "VLMediaAppDatabase"
                )
                    .build()
            }

            return appRoomDatabase!!
        }
    }
}