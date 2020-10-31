package com.example.vlmedia.main

import androidx.appcompat.app.AppCompatActivity
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.vlmedia.AppUnitTestSuite
import com.example.vlmedia.database.CharDao
import com.example.vlmedia.database.Character
import com.example.vlmedia.testUtils.MainCoroutineScopeRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.*
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class MainRepositoryTest : AppUnitTestSuite() {

    private lateinit var repository: MainRepository

    @Mock
    private lateinit var context: AppCompatActivity

    @Mock
    private lateinit var charDao: CharDao

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    override fun setUpBefore() {
        repository = spy(MainRepository(context, charDao, Dispatchers.Unconfined))
    }

    @Test
    fun shouldGetCharacterList() = coroutineScope.runBlockingTest {
        val list = listOf(mock(Character::class.java))
        doReturn(list).`when`(charDao).getCharacterList()

        val listFromRepository = repository.getCharacterList()

        assertEquals(list, listFromRepository)
    }

    @Test
    fun shouldGetCharacterById() = coroutineScope.runBlockingTest {
        val character = mock(Character::class.java)
        doReturn(character).`when`(charDao).getCharacterById("characterId")

        val characterFromRepository = repository.getCharacterById("characterId")

        assertEquals(character, characterFromRepository)
    }
}