package com.example.villagenetworkapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userNumber: EditText = findViewById(R.id.etPhone)
        val userPassword: EditText = findViewById(R.id.etPassword)
        val btnLogIn: LinearLayout = findViewById(R.id.btnLogin)
        val btnLinkToReg: TextView = findViewById(R.id.linkRegister)

        btnLinkToReg.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        btnLogIn.setOnClickListener {
            val number = userNumber.text.toString().trim()
            val password = userPassword.text.toString().trim()

            if(number == "" || password == "")
                Toast.makeText(this, "Необходимо проверить заполненость всех полей", Toast.LENGTH_LONG).show()
            else {

                val db = DbHelper(this, null)
                val isLogIn = db.getUser(number, password)

                if(isLogIn) {
                    Toast.makeText(this, "Пользователь $number успешно вошел!", Toast.LENGTH_LONG).show()
                    userNumber.text.clear()
                    userPassword.text.clear()

                    val intent = Intent(this, NewsActivity::class.java)
                    startActivity(intent)
                } else
                    Toast.makeText(this, "Пользователь $number не найден!", Toast.LENGTH_LONG).show()
            }
        }
    }
}