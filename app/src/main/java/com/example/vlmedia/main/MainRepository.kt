package com.example.vlmedia.main

import androidx.appcompat.app.AppCompatActivity
import com.example.vlmedia.database.AppRoomDatabase
import com.example.vlmedia.database.CharDao
import com.example.vlmedia.database.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MainRepository(
        private val activity: AppCompatActivity,
        private val charDao: CharDao = AppRoomDatabase.getDatabase(activity).charDao(),
        private val coroutineContext: CoroutineContext = Dispatchers.IO
) {

    suspend fun getCharacterList() = withContext(coroutineContext) {
        charDao.getCharacterList()
    }

    suspend fun addCharacter(character: Character) = withContext(coroutineContext) {
        charDao.addCharacter(character)
    }

    fun getCharacterById(id: String) = charDao.getCharacterById(id)
}