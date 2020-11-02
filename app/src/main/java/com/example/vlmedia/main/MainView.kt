package com.example.vlmedia.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vlmedia.R
import com.example.vlmedia.database.DatabaseManager
import com.example.vlmedia.main.adapter.MainAdapter
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.main.*

class MainView : AppCompatActivity() {

    private val viewModel by lazy { MainViewModel(MainRepository(this)) }
    private val adapter by lazy { MainAdapter(viewModel, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        title = "Characters"

        Firebase.analytics.logEvent("MAIN_SCREEN") {
            param(FirebaseAnalytics.Param.SCREEN_CLASS, "MainView.kt")
        }


        recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this@MainView)
            this.adapter = this@MainView.adapter
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.characterList.observe(this, {
            adapter.notifyDataSetChanged()
        })
    }

    override fun onResume() {
        super.onResume()
        DatabaseManager.loadDatabase(viewModel)
        viewModel.loadData()
    }
}