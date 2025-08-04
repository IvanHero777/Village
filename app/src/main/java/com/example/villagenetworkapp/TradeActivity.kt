package com.example.villagenetworkapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class TradeActivity : AppCompatActivity() {

    companion object {
        private const val REQ_ADD_PRODUCT = 1001
    }

    private lateinit var llProducts: LinearLayout
    private val products by lazy { Prefs.load(this) }   // коллекция обновляется in‑place

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trade)          // ваш исходный layout

        llProducts  = findViewById(R.id.llProducts)
        val btnPlus = findViewById<ImageButton>(R.id.btnAddItem)

        btnPlus.setOnClickListener {
            startActivityForResult(
                Intent(this, TradeCreateActivity::class.java),
                REQ_ADD_PRODUCT
            )
        }

        renderList()    // первичное заполнение
    }

    private fun renderList() {
        llProducts.removeAllViews()
        val inflater = LayoutInflater.from(this)

        products.forEach { p ->
            val card = inflater.inflate(R.layout.item_product, llProducts, false)

            Glide.with(card).load(Uri.parse(p.image)).into(card.findViewById(R.id.ivPhoto))
            card.findViewById<android.widget.TextView>(R.id.tvName ).text  = p.name
            card.findViewById<android.widget.TextView>(R.id.tvPrice).text = "Цена: ${p.price} ₽"
            card.findViewById<android.widget.TextView>(R.id.tvDesc ).text  = p.desc
            card.findViewById<android.widget.TextView>(R.id.tvPhone).text = "Телефон: ${p.phone}"

            llProducts.addView(card)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_ADD_PRODUCT && resultCode == Activity.RESULT_OK) {
            // список уже сохранён => перечитать
            products.clear()
            products.addAll(Prefs.load(this))
            renderList()
            Toast.makeText(this, "Товар добавлен", Toast.LENGTH_SHORT).show()
        }
    }
}
