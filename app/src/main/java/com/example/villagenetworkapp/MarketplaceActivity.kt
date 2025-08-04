package com.example.villagenetworkapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MarketplaceActivity : AppCompatActivity() {

    private lateinit var llProducts: LinearLayout

    private val addItemLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { intent -> addCardFromResult(intent) }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trade)         // ваш главный XML

        llProducts = findViewById(R.id.llProducts)

        findViewById<ImageButton>(R.id.btnAddItem).setOnClickListener {
            addItemLauncher.launch(Intent(this, AddItemActivity::class.java))
        }
    }

    private fun addCardFromResult(intent: Intent) {
        val card = layoutInflater.inflate(R.layout.item_product_card, llProducts, false)

        val img  = intent.getStringExtra("IMG")?.takeIf { it.isNotEmpty() }?.let { Uri.parse(it) }
        val name = intent.getStringExtra("NAME") ?: ""
        val price= intent.getStringExtra("PRICE") ?: ""
        val phone= intent.getStringExtra("PHONE") ?: ""

        card.findViewById<ImageView>(R.id.ivProduct).apply {
            if (img != null) setImageURI(img) else setImageResource(R.drawable.ic_add_photo)
        }
        card.findViewById<TextView>(R.id.tvName ).text = name
        card.findViewById<TextView>(R.id.tvPrice).text = "Продукты питания • $price руб"
        card.findViewById<TextView>(R.id.tvPhone).text = "Телефон: $phone"

        llProducts.addView(card)
        // Перемотать вниз, чтобы пользователь видел добавленный товар:
        llProducts.post { findViewById<androidx.core.widget.NestedScrollView>(R.id.scrollContent).fullScroll(android.view.View.FOCUS_DOWN) }
    }
}
