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

        Log.d(TAG, "This is a first log!")
        val button: Button = findViewById(R.id.gallery) as Button
        button.setOnClickListener { view ->
            Log.d(TAG, "Click!")
        }

        Log.d(TAG, "This is a second log!")
        val button2: Button = findViewById(R.id.joint) as Button
        button.setOnClickListener { view ->
            Log.d(TAG, "Click!")
        }



    }

}
