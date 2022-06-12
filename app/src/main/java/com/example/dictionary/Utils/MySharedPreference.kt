package com.example.dictionary.Utils

import android.content.Context
import android.content.SharedPreferences
import com.example.dictionary.Models.Word
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

object MySharedPreference {
    private const val NAME = "KeshXotiraga"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context?) {
        preferences = context!!.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var uzbWords: ArrayList<Word>
        get() = gsonStringToArray(preferences.getString("uzbek", "[]")!!)
        set(value) = preferences.edit {
            it.putString("uzbek", arrayToGsonString(value))
        }

    var engWords: ArrayList<Word>
        get() = gsonStringToArray(preferences.getString("english", "[]")!!)
        set(value) = preferences.edit {
            it.putString("english", arrayToGsonString(value))
        }

    fun arrayToGsonString(arrayList: ArrayList<Word>): String {
        return Gson().toJson(arrayList)
    }

    fun gsonStringToArray(gsonString: String): ArrayList<Word> {
        val typeToken = object : TypeToken<ArrayList<Word>>() {}.type
        return Gson().fromJson(gsonString, typeToken)
    }
}
