package com.example.moebutapworks.picsfixer

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import com.adobe.creativesdk.aviary.AdobeImageIntent


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

        Log.d(TAG, "garrely button")
        val gallery: Button = findViewById(R.id.gallery) as Button
        gallery.setOnClickListener{
            launchGallery()
        }

        Log.d(TAG, "joint button")
        val joint: Button = findViewById(R.id.joint) as Button
        joint.setOnClickListener {
            newSampleActivity()
        }

        Log.d(TAG, "photo button")
        val photo: Button = findViewById(R.id.photobutton) as Button
        photo.setOnClickListener {
            launchCamera()
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
        val REQUEST_CODE_CAMERA = 10
        val REQUEST_CODE_EDITOR = 20
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                REQUEST_CODE_EDITOR -> {
                    editedFileUri = data?.getParcelableExtra(AdobeImageIntent.EXTRA_OUTPUT_URI)
                    Log.d(TAG, "editor editedFileUri: " + editedFileUri)
                    image?.setImageURI(editedFileUri)
                }
                REQUEST_CODE_CAMERA -> {
                    Log.d(TAG, "camera fileUri: " + fileUri)
                    launchEditor(fileUri)
                }
                REQUEST_CODE_GALLERY -> {
                    fileUri = data?.data
                    Log.d(TAG, "gallery fileUri " + fileUri)
                    launchEditor(fileUri)
                }
            }
        }
    }

    private fun newSampleActivity() {
        val intent = Intent()
        intent.setClass(this, NewSampleActivity::class.java)
        startActivity(intent)
    }

    private fun launchCamera(){
        val intent = Intent()
        intent.action = MediaStore.ACTION_IMAGE_CAPTURE
        //create a file to save the image
        fileUri = FileUtil.getOutputMediaFileUri(this, FileUtil.MEDIA_TYPE_IMAGE)
        //set the image file name
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
        startActivityForResult(intent, REQUEST_CODE_CAMERA)
    }

    private fun launchEditor(uri: Uri?) {
        val imageEditorIntent = AdobeImageIntent.Builder(this)
                .setData(uri)
                .build()
        startActivityForResult(imageEditorIntent, REQUEST_CODE_EDITOR)
    }

}


