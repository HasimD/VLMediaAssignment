package com.example.vlmedia.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.vlmedia.AppUnitTestSuite
import com.example.vlmedia.database.Character
import com.example.vlmedia.testUtils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class MainViewModelTest : AppUnitTestSuite() {

    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var repository: MainRepository

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    override fun setUpBefore() {
        viewModel = spy(MainViewModel(repository))
    }

    @Test
    fun shouldLoadData() = coroutineScope.runBlockingTest {
        val list = listOf(mock(Character::class.java))
        doReturn(list).`when`(repository).getCharacterList()

        viewModel.loadData()
        assertEquals(list, viewModel.characterList.value)
    }

    @Test
    fun shouldGetData() = coroutineScope.runBlockingTest {
        val character = mock(Character::class.java)
        doReturn(character).`when`(repository).getCharacterById("characterId")

        val characterFromViewModel = viewModel.getCharacterById("characterId")

        assertEquals(character, characterFromViewModel)
    }
}