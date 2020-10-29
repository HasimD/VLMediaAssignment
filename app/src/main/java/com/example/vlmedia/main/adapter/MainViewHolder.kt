package com.example.vlmedia.main.adapter

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vlmedia.R

class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val row: LinearLayout = view.findViewById(R.id.linearLayout_row)
    val image: ImageView = view.findViewById(R.id.imageView_char)
    val name: TextView = view.findViewById(R.id.textView_name)
}