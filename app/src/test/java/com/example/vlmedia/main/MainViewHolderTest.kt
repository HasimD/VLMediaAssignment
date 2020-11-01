package com.example.vlmedia.main

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.vlmedia.AppUnitTestSuite
import com.example.vlmedia.R
import com.example.vlmedia.main.adapter.MainViewHolder
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import kotlin.test.Test
import kotlin.test.assertFailsWith

class MainViewHolderTest : AppUnitTestSuite() {
    private lateinit var viewHolder: MainViewHolder

    @Mock
    private lateinit var view: View

    override fun setUpBefore() {
        doReturn(mock(LinearLayout::class.java)).`when`(view)
            .findViewById<LinearLayout>(R.id.linearLayout_row)
        doReturn(mock(ImageView::class.java)).`when`(view)
            .findViewById<ImageView>(R.id.imageView_char)
        doReturn(mock(TextView::class.java)).`when`(view).findViewById<TextView>(R.id.textView_name)
    }

    @Test
    fun shouldInitializeWithNoExceptions() {
        viewHolder = MainViewHolder(view)
    }

    @Test
    fun shouldThrowException() {
        doReturn(null).`when`(view).findViewById<TextView>(R.id.textView_name)

        assertFailsWith(NullPointerException::class) {
            viewHolder = MainViewHolder(view)
        }
    }
}