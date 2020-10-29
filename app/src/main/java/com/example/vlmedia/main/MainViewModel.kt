package com.example.vlmedia.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vlmedia.database.Character
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val mutableCharacterList = MutableLiveData<List<Character>>()

    val characterList: LiveData<List<Character>>
        get() = mutableCharacterList

    fun loadData() = viewModelScope.launch {
        mutableCharacterList.value = repository.getCharacterList()
    }

    fun addCharacter(character: Character) = viewModelScope.launch {
        repository.addCharacter(character)
        loadData()
    }

    fun getCharacterById(id: String) = repository.getCharacterById(id)
}