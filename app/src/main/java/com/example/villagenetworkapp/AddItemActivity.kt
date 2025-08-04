package com.example.villagenetworkapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class AddItemActivity : AppCompatActivity() {

    private var imageUri: Uri? = null
    private lateinit var ivPreview: ImageView
    private val pickImage =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                imageUri = it
                ivPreview.setImageURI(it)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trade_create)

        ivPreview = findViewById(R.id.ivPreview)

        findViewById<Button>(R.id.btnPickImage).setOnClickListener {
            pickImage.launch("image/*")
        }

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val name  = findViewById<EditText>(R.id.etName).text.toString().trim()
            val price = findViewById<EditText>(R.id.etPrice).text.toString().trim()
            val phone = findViewById<EditText>(R.id.etPhone).text.toString().trim()

            if (name.isBlank() || price.isBlank() || phone.isBlank()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val data = Intent().apply {
                putExtra("NAME",  name)
                putExtra("PRICE", price)
                putExtra("PHONE", phone)
                putExtra("IMG",   imageUri?.toString() ?: "")
            }
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }
}
