package com.example.vlmedia.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
abstract class CharDao {

    @Insert
    abstract fun insert(character: Character)

    @Query("SELECT * FROM CHARACTER WHERE ID = :id")
    abstract fun getCharacterById(id: String): Character?

    @Query("SELECT * FROM CHARACTER")
    abstract fun getCharacterList(): MutableList<Character>

    fun addCharacter(character: Character) = insert(character)
}