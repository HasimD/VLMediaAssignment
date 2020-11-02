package com.example.vlmedia.character

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.vlmedia.R
import com.example.vlmedia.testUtils.Cache
import com.example.vlmedia.testUtils.CommonUtils
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.character.*

class CharacterView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character)
        title = "Character Information"

        Firebase.analytics.logEvent("CHARACTER_SCREEN") {
            param(FirebaseAnalytics.Param.SCREEN_CLASS, "CharacterView.kt")
        }

        val character = Cache.currentCharacter
        if (character == null) {
            finish()
        }

        textView_name.text = character!!.name
        textView_status.apply {
            text = character.status
            when (character.status) {
                "Alive" -> setTextColor(ContextCompat.getColor(this@CharacterView, R.color.alive))
                "Dead" -> setTextColor(ContextCompat.getColor(this@CharacterView, R.color.dead))
            }
        }
        textView_location.text = character.location
        imageView_char.apply {
            CommonUtils.loadImageByGlide(
                    character.image,
                    this,
                    this@CharacterView
            )
        }
    }
}