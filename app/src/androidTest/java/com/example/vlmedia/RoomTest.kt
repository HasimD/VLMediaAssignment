package com.example.vlmedia

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vlmedia.database.AppRoomDatabase
import com.example.vlmedia.database.CharDao
import com.example.vlmedia.database.Character
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RoomTest {
    private lateinit var charDao: CharDao
    private lateinit var db: AppRoomDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppRoomDatabase::class.java
        ).build()
        charDao = db.charDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun addCharAndFindById() {
        val character = Character("testID", "test", "test", "test", "test")
        charDao.addCharacter(character)

        val characterFromRoom = charDao.getCharacterById("testID")
        assertThat(characterFromRoom, equalTo(character))
    }

    @Test
    @Throws(Exception::class)
    fun notAddCharAndFindById() {
        val charById = charDao.getCharacterById("id")
        assertNull(charById)
    }

    @Test
    @Throws(Exception::class)
    fun addCharsAndGetItemList() {
        charDao.apply {
            this.addCharacter(Character("testID1", "test", "test", "test", "test"))
            this.addCharacter(Character("testID2", "test", "test", "test", "test"))
        }
        val charList = charDao.getCharacterList()

        assertNotNull(charList)
        assertThat(charList.size, equalTo(2))
    }
}