package com.example.villagenetworkapp

data class Product(
    val name:  String,
    val desc:  String,
    val price: String,
    val phone: String,
    /** content‑URI картинки (в виде строки) */
    val image: String
)
