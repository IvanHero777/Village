package com.example.villagenetworkapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class TradeCreateActivity : AppCompatActivity() {

    companion object {
        private const val REQ_PHOTO = 2001
    }

    private lateinit var ivPreview: ImageView
    private lateinit var etName: EditText
    private lateinit var etDesc: EditText
    private lateinit var etPrice: EditText
    private lateinit var etPhone: EditText
    private var photoUri: Uri? = null        // выбранная картинка

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trade_create)   // XML из предыдущего ответа

        ivPreview = findViewById(R.id.ivPreview)
        etName    = findViewById(R.id.etName)
        etDesc    = findViewById(R.id.etDesc)
        etPrice   = findViewById(R.id.etPrice)
        etPhone   = findViewById(R.id.etPhone)

        findViewById<Button>(R.id.btnPickImage).setOnClickListener {
            val pick = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(pick, REQ_PHOTO)
        }

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            if (validate()) saveAndExit()        // проверяем и сохраняем
        }
    }

    private fun validate(): Boolean {
        if (photoUri == null)        { toast("Выберите фото") ; return false }
        if (etName.text.isBlank())   { toast("Введите название") ; return false }
        if (etPrice.text.isBlank())  { toast("Введите цену") ; return false }
        if (etPhone.text.isBlank())  { toast("Введите телефон") ; return false }
        return true
    }

    private fun saveAndExit() {
        val newProduct = Product(
            name  = etName.text.toString(),
            desc  = etDesc.text.toString(),
            price = etPrice.text.toString(),
            phone = etPhone.text.toString(),
            image = photoUri.toString()
        )
        val list = Prefs.load(this)
        list.add(0, newProduct)               // в начало списка
        Prefs.save(this, list)

        setResult(Activity.RESULT_OK)
        finish()                              // возвращаемся в TradeActivity
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_PHOTO && resultCode == Activity.RESULT_OK) {
            photoUri = data?.data
            ivPreview.setImageURI(photoUri)
        }
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
