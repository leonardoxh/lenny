package com.gihub.leonardoxh.lenny

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.BufferedReader
import java.io.InputStreamReader


object FaceLoadUtils {

    const val ASSET_FILE_PATH = "faces.json"

    fun loadFacesFromAsset(context: Context): List<String> {
        val adapter: JsonAdapter<List<String>> = Moshi.Builder()
                .build()
                .adapter(Types.newParameterizedType(List::class.java, String::class.java))
        return adapter.fromJson(jsonFromAsset(context))
    }

    fun jsonFromAsset(context: Context, filePath: String = ASSET_FILE_PATH): String {
        val buf = StringBuilder()
        val json = context.assets.open(filePath)
        val stream = BufferedReader(InputStreamReader(json, "UTF-8"))
        stream.forEachLine { line ->
            buf.append(line)
        }
        stream.close()
        return buf.toString()
    }

}