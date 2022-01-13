package com.my.templatelib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.my.templatelib.R.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        findViewById<TextView>(id.textView).setOnClickListener {
            startActivity(Intent(this, com.my.ekyc.ui.main.activity.MainActivity::class.java))
        }
    }
}