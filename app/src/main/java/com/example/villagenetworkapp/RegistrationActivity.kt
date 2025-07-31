package com.example.villagenetworkapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etSurname: EditText
    private lateinit var etPhone: EditText
    private lateinit var etAddress: EditText
    private lateinit var spGender: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        etName     = findViewById(R.id.etName)
        etSurname  = findViewById(R.id.etSurname)
        etPhone    = findViewById(R.id.etPhone)
        etAddress  = findViewById(R.id.etAddress)
        spGender   = findViewById(R.id.spGender)

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener { finish() }
        findViewById<Button>(R.id.btnRegister).setOnClickListener { saveAndNext() }
    }

    private fun saveAndNext() {
        val prefs = getSharedPreferences("user_prefs", MODE_PRIVATE)
        prefs.edit()
            .putString("NAME", etName.text.toString())
            .putString("SURNAME", etSurname.text.toString())
            .putString("PHONE", etPhone.text.toString())
            .putString("ADDRESS", etAddress.text.toString())
            .putString("GENDER", spGender.selectedItem.toString())
            .apply()

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
