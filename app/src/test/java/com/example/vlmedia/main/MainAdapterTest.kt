package com.example.vlmedia.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vlmedia.AppUnitTestSuite
import com.example.vlmedia.R
import com.example.vlmedia.database.Character
import com.example.vlmedia.main.adapter.MainAdapter
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.spy
import kotlin.test.assertEquals

class MainAdapterTest : AppUnitTestSuite() {
    private lateinit var adapter: MainAdapter

    @Mock
    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var activity: AppCompatActivity

    @Mock
    private lateinit var inflater: LayoutInflater

    @Mock
    private lateinit var viewGroup: ViewGroup

    @Mock
    private lateinit var view: View

    @Mock
    private lateinit var row: LinearLayout

    @Mock
    private lateinit var image: ImageView

    @Mock
    private lateinit var name: TextView

    @Mock
    private lateinit var character: Character

    private lateinit var liveData: LiveData<List<Character>>
    private lateinit var charList: MutableList<Character>

    override fun setUpBefore() {
        liveData = spy(MutableLiveData())
        charList = mutableListOf()

        doReturn(charList).`when`(liveData).value
        doReturn(liveData).`when`(viewModel).characterList
        doReturn(inflater).`when`(activity).getSystemService(Context.LAYOUT_INFLATER_SERVICE)

        adapter = spy(MainAdapter(viewModel, activity))
        doReturn(view).`when`(inflater).inflate(R.layout.main_row, viewGroup, false)

        doReturn(row).`when`(view).findViewById<LinearLayout>(R.id.linearLayout_row)
        doReturn(image).`when`(view).findViewById<ImageView>(R.id.imageView_char)
        doReturn(name).`when`(view).findViewById<TextView>(R.id.textView_name)
    }

    @Test
    fun getItemCount_withNullLiveData() {
        doReturn(null).`when`(liveData).value
        assertEquals(0, adapter.itemCount)
    }

    @Test
    fun getItemCount_withSomeData() {
        charList.add(character)
        assertEquals(1, adapter.itemCount)
    }
}