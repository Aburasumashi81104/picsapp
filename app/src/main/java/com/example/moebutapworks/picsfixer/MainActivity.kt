package com.example.moebutapworks.picsfixer

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView


class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    private var image: ImageView? =null
    private var fileUri: Uri? = null
    private var editedFileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "Slelect Theme!!")
        val theme: Button = findViewById(R.id.theme) as Button
        theme.setOnClickListener { view ->
            Log.d(TAG, "theme button is Clicked!")
        }

        Log.d(TAG, "This is a first log!!")
        val gallery: Button = findViewById(R.id.gallery) as Button
        gallery.setOnClickListener{
            launchGallery()
        }

        Log.d(TAG, "This is a second log!!")
        val joint: Button = findViewById(R.id.joint) as Button
        joint.setOnClickListener {
            newSampleActivity()
        }

        Log.d(TAG, "This is a first log!")
        val photo: Button = findViewById(R.id.photobutton) as Button
        photo.setOnClickListener { view ->
            Log.d(TAG, "photo button is Clicked!")
        }

    }

    private fun launchGallery(){
        val intent = Intent()
        intent.action = Intent.ACTION_PICK
        intent.data =
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent,REQUEST_CODE_GALLERY)
    }

    companion object {
        val REQUEST_CODE_GALLERY = 30
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                REQUEST_CODE_GALLERY -> {
                    fileUri = data?.data
                    Log.d(TAG, "gallery fileUri " + fileUri)
                }
            }
        }
    }

    private fun newSampleActivity() {
        val intent = Intent()
        intent.setClass(this, NewSampleActivity::class.java)
        startActivity(intent)
    }
}
