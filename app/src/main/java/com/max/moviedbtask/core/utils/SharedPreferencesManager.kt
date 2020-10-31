package com.max.moviedbtask.core.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * Created by µðšţãƒâ ™ on 29/10/2020.
 *  ->
 */

object SharedPreferencesManager {
    private lateinit var prefs: SharedPreferences

    private const val PREFS_NAME = "params"

    const val TOKEN = "TOKEN"
    const val IMAGE_URL = "IMAGE_URL"

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
    }

    fun getToken(): String? = prefs.getString(TOKEN, "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYzAwMDM2Y2U0ODU5YWE4NTFhZTFhMWQ1ODQ4NDg3NyIsInN1YiI6IjVmOWE3OWE1YjRhNTQzMDAzOTM3NjVhMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.kBhr91-I7CtgT8XxQ4tyxdiuPnkmg94BoIJFLenp0BY")

    fun saveToken(value: String) {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        with(prefsEditor) {
            putString(TOKEN, value)
            commit()
        }
    }

    fun getImageUrl(): String? = prefs.getString(IMAGE_URL, "")

    fun saveImageUrl(value: String) {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        with(prefsEditor) {
            putString(IMAGE_URL, value)
            commit()
        }
    }

    fun <T> saveList(key: String, list: MutableList<T>) {
        val gson = Gson()
        val json = gson.toJson(list)
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        with(prefsEditor) {
            putString(key, json)
            commit()
        }
    }

    fun <T> getList(key: String): MutableList<T> {
        val gson = Gson()
        val json = prefs.getString(key, "")
        val type: Type = object : TypeToken<MutableList<T>>() {}.type
        return gson.fromJson(json, type)
    }

    inline fun <reified T> fromJson(json: String): MutableList<T> {
        return Gson().fromJson(json, object : TypeToken<MutableList<T>>() {}.type)
    }
}