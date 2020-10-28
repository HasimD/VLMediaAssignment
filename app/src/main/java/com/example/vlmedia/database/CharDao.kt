package com.example.vlmedia.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
abstract class CharDao {

    @Insert
    abstract fun insert(character: Character)

    @Query("SELECT * FROM CHARACTER WHERE ID = :id")
    abstract fun getCharacterById(id: String): LiveData<Character>

    @Query("SELECT * FROM CHARACTER")
    abstract fun getCharacterList(): LiveData<MutableList<Character>>

    fun addCharacter(character: Character) = insert(character)
}