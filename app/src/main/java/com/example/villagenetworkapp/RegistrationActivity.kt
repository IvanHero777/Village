package com.example.villagenetworkapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
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

        val userName: EditText = findViewById(R.id.etName)
        val userSurname: EditText = findViewById(R.id.etSurname)
        val userNumber: EditText = findViewById(R.id.etPhone)
        val userPassword: EditText = findViewById(R.id.etPassword)
        val userAddress: EditText = findViewById(R.id.etAddress)
        val buttonReg: Button = findViewById(R.id.btnRegister)
        val btnLinkToLogIn: ImageButton = findViewById(R.id.btnBack)

        btnLinkToLogIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        buttonReg.setOnClickListener {
            val name = userName.text.toString().trim()
            val surname = userSurname.text.toString().trim()
            val number = userNumber.text.toString().trim()
            val password = userPassword.text.toString().trim()
            val address = userAddress.text.toString()

            if (name == "" || surname == "" || number == "" || password == "" || address == "")
                Toast.makeText(
                    this,
                    "Необходимо проверить заполненость всех полей",
                    Toast.LENGTH_LONG
                ).show()
            else {
                val user = User(name, surname, number, password, address)

                val db = DbHelper(this, null)
                db.addUser(user)
                Toast.makeText(
                    this,
                    "Пользователь $number успешно зарегестриирован!",
                    Toast.LENGTH_LONG
                ).show()

                userName.text.clear()
                userSurname.text.clear()
                userNumber.text.clear()
                userPassword.text.clear()
                userAddress.text.clear()
            }
        }
    }
}
