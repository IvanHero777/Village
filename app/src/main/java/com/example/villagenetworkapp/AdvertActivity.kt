package com.example.villagenetworkapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AdvertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_advert)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnLinkToMarket: ImageButton = findViewById(R.id.btnMarket)

        btnLinkToMarket.setOnClickListener {
            val intent = Intent(this, MarketActivity::class.java)
            startActivity(intent)
        }

        val btnLinkToAnons: ImageButton = findViewById(R.id.btnNews)

        btnLinkToAnons.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
        }

        val btnLinkToService: ImageButton = findViewById(R.id.btnService)

        btnLinkToService.setOnClickListener {
            val intent = Intent(this, ServiceActivity::class.java)
            startActivity(intent)
        }

        val btnLinkToOpros: ImageButton = findViewById(R.id.btnPolls)

        btnLinkToOpros.setOnClickListener {
            val intent = Intent(this, PollsActivity::class.java)
            startActivity(intent)
        }

        val btnLinkToProfile: ImageButton = findViewById(R.id.btnProfile)

        btnLinkToProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}