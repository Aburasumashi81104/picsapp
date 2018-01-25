package com.example.moebutapworks.picsfixer

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import com.adobe.creativesdk.aviary.AdobeImageIntent
import com.example.moebutapworks.picsfixer.R.color.bbpink
import com.example.moebutapworks.picsfixer.R.color.blue
import com.example.moebutapworks.picsfixer.R.styleable.View


class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    private var image: ImageView? =null
    private var fileUri: Uri? = null
    private var editedFileUri: Uri? = null
    private val parentLayout: RelativeLayout by lazy{ findViewById(R.id.mainlayout) as RelativeLayout}
    private var popupMenu: PopupMenu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "Slelect Theme!!")
        val theme: Button = findViewById(R.id.theme) as Button
        theme.setOnClickListener{ v ->
            //Log.d(TAG, "theme button is Clicked!!")
            showPopup(v)
        }

        Log.d(TAG, "garrely button")
        val gallery: Button = findViewById(R.id.gallery) as Button
        gallery.setOnClickListener{
            launchGallery()
        }



        Log.d(TAG, "photo button")
        val photo: Button = findViewById(R.id.photobutton) as Button
        photo.setOnClickListener {
            launchCamera()
        }

        val layout = findViewById(R.id.mainlayout)
        layout.setBackgroundColor(Color.BLUE)

        val toolbar = findViewById(R.id.home_toolbar) as Toolbar
        toolbar.title = "Home"
        toolbar.setTitleTextColor(Color.WHITE)

    }

    private fun launchGallery(){
        val intent = Intent()
        intent.action = Intent.ACTION_PICK
        intent.data =
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent,REQUEST_CODE_GALLERY)
    }

    private fun showPopup(view:View){


        if (popupMenu == null){
            popupMenu = PopupMenu (this, view)
            popupMenu?.menuInflater?.inflate(R.menu.popup, popupMenu?.menu)
        }

        popupMenu?.setOnMenuItemClickListener { menu ->
            val toolbar = findViewById(R.id.home_toolbar)
            val background = findViewById(R.id.mainlayout)
           when( menu.itemId){

               R.id.theme01 ->{
                   //ボタン押された時の処理
                   toolbar.setBackgroundColor(Color.RED)
                   background.setBackgroundColor(Color.BLACK)
               }

               R.id.theme02 -> {
                   //ボタン押された時の処理
                   toolbar.setBackgroundColor(getResources().getColor(bbpink))
                   background.setBackgroundColor(Color.GRAY)
               }

               R.id.theme03 ->{
                   //ボタン押された時の処理
                   toolbar.setBackgroundColor(Color.BLACK)
                   background.setBackgroundColor(Color.WHITE)
               }

               else ->{
                   //それ以外
               }
           }
            return@setOnMenuItemClickListener true
        }
        popupMenu?.show()
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


