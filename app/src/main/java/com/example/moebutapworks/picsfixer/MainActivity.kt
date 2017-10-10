package com.example.moebutapworks.picsfixer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Gallery

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "This is a first log!")
        val Gallery: Button = findViewById(R.id.Gallery) as Button
        button.setOnClickListener { view ->
            android.util.Log.d(TAG, "Click!")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "This is a second log!")
        val Joint: Button = findViewById(R.id.Joint) as Button
        button.setOnClickListener { view ->
            android.util.Log.d(TAG, "Click!")
        }
    }

}
