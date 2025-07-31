package com.example.villagenetworkapp

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class NewsTouchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_touch)

        val resId = intent.getIntExtra("img", R.drawable.news1_big)
        findViewById<ImageView>(R.id.ivFull).setImageResource(resId)
    }
}
