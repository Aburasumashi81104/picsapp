package com.example.moebutapworks.picsfixer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button


class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "Slelect Theme!!")
        val theme: Button = findViewById(R.id.theme) as Button
        theme.setOnClickListener { view ->
            Log.d(TAG, "Click!")
        }

        Log.d(TAG, "This is a first log!!")
        val gallery: Button = findViewById(R.id.gallery) as Button
        gallery.setOnClickListener { view ->
            Log.d(TAG, "Click!")
        }

        Log.d(TAG, "This is a second log!!")
        val joint: Button = findViewById(R.id.joint) as Button
        joint.setOnClickListener { view ->
            Log.d(TAG, "Click!")
        }

        Log.d(TAG, "This is a first log!")
        val photo: Button = findViewById(R.id.photo) as Button
        photo.setOnClickListener { view ->
            Log.d(TAG, "Click!")
        }

    }

}
