package com.example.villagenetworkapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // назначаем клики четырём карточкам
        findViewById<View>(R.id.cardNews1).setOnClickListener {
            openNews(R.drawable.news1_big)
        }
        findViewById<View>(R.id.cardNews2).setOnClickListener {
            openNews(R.drawable.news4_big)
        }
        findViewById<View>(R.id.cardNews3).setOnClickListener {
            openNews(R.drawable.news3_big)
        }
        findViewById<View>(R.id.cardNews4).setOnClickListener {
            openNews(R.drawable.news4_big)
        }
        findViewById<View>(R.id.btnMarketplace).setOnClickListener {
            startActivity(Intent(this, MarketplaceActivity::class.java))
        }
    }

    private fun openNews(imageRes: Int) {
        startActivity(Intent(this, NewsTouchActivity::class.java).apply {
            putExtra("img", imageRes)
        })
    }

}
