package com.example.vlmedia.database

import android.content.Context
import com.example.vlmedia.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

object DatabaseManager {

    fun loadDatabase(context: Context, viewModel: MainViewModel) {
        val client = OkHttpClient()

        // request for Game List
        val request = Request.Builder()
            .url("https://rickandmortyapi.com/api/character")
            .get()
            .build()

        Thread {
            try {
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val jsonResponse = JSONObject(response.body!!.string())
                    val jsonCharacterList = jsonResponse.getJSONArray("results")

                    for (i in 0 until jsonCharacterList.length()) {
                        val jsonCharacter = JSONObject(jsonCharacterList[i].toString())
                        val id = jsonCharacter.getString("id")

                        // if nothing is new, then skip.
                        if (viewModel.getCharacterById(id) != null) {
                            continue
                        }

                        val name = jsonCharacter.getString("name")
                        val image = jsonCharacter.getString("image")
                        val status = jsonCharacter.getString("status")
                        val location =
                            JSONObject(jsonCharacter.getString("location")).getString("name")

                        viewModel.addCharacter(Character(id, name, image, status, location))
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }.start()
    }
}