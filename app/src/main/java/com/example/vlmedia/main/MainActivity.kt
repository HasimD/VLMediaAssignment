package com.example.vlmedia.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vlmedia.R
import com.example.vlmedia.database.DatabaseManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
    }

    override fun onResume() {
        super.onResume()
        DatabaseManager.loadDatabase(this)
    }
}