package com.gihub.leonardoxh.lenny

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

class MainActivity : Activity(), FacesAdapter.OnFaceClick {

    val clipboardManager by lazy {
        getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        with (facesList) {
            setHasFixedSize(true)
            facesList.layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        LoadFaces().executeOnExecutor(Executors.newSingleThreadExecutor())
    }

    override fun onFaceClick(face: String) {
        clipboardManager.primaryClip = ClipData.newPlainText(face, face)
        Toast.makeText(this, R.string.copied_to_clipboard, Toast.LENGTH_SHORT)
                .show()
    }

    inner class LoadFaces : AsyncTask<Void, Void, List<String>>() {

        override fun doInBackground(vararg args: Void?): List<String> {
            return FaceLoadUtils.loadFacesFromAsset(this@MainActivity)
        }

        override fun onPostExecute(result: List<String>) {
            facesList.adapter = FacesAdapter(result, this@MainActivity)
        }

    }

}
