package com.example.vlmedia.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.vlmedia.R
import com.example.vlmedia.character.CharacterView
import com.example.vlmedia.database.Character
import com.example.vlmedia.main.MainViewModel
import com.example.vlmedia.testUtils.Cache
import com.example.vlmedia.testUtils.CommonUtils

class MainAdapter(
    private val viewModel: MainViewModel,
    private val activity: AppCompatActivity
) : RecyclerView.Adapter<MainViewHolder>() {

    private val inflater: LayoutInflater =
        activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private val charList: List<Character>
        get() = viewModel.characterList.value ?: emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainViewHolder(inflater.inflate(R.layout.main_row, parent, false))

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val character = charList[position]

        holder.row.setOnClickListener {
            Cache.currentCharacter = character
            val intent = Intent(activity, CharacterView::class.java)
            activity.startActivity(intent)
        }
        holder.image.apply { CommonUtils.loadImageByGlide(character.image, this, activity) }
        holder.name.text = character.name
    }

    override fun getItemCount() = charList.size
}