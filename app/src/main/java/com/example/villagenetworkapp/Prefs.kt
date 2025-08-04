package com.example.villagenetworkapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Prefs {

    private const val FILE  = "products_store"
    private const val KEY   = "products_json"
    private val   gson     = Gson()
    private val   listType = object : TypeToken<MutableList<Product>>() {}.type

    fun load(ctx: Context): MutableList<Product> {
        val json = ctx.getSharedPreferences(FILE, Context.MODE_PRIVATE).getString(KEY, null)
        return json?.let { gson.fromJson(it, listType) } ?: mutableListOf()
    }

    fun save(ctx: Context, list: List<Product>) {
        val json = gson.toJson(list)
        ctx.getSharedPreferences(FILE, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY, json)
            .apply()
    }
}
