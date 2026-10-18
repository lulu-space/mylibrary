package com.example.mylibrary

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Subrecource : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_subrecource)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    val imageButton12: ImageButton = findViewById(R.id.imageButton12)
    val imageButton13: ImageButton = findViewById(R.id.imageButton13)
    val imageButton2: ImageButton = findViewById(R.id.imageButton2)
    val imageButton3: ImageButton = findViewById(R.id.imageButton3)
    val imageButton4: ImageButton = findViewById(R.id.imageButton4)
    val imageButton5: ImageButton = findViewById(R.id.imageButton5)
    val imageButton6: ImageButton = findViewById(R.id.imageButton6)
    val imageButton7: ImageButton = findViewById(R.id.imageButton7)
    val imageButton9: ImageButton = findViewById(R.id.imageButton9)

        imageButton3.setOnClickListener {
            val intent = Intent(this, Viewrecourse::class.java)
            startActivity(intent)
        }
        imageButton6.setOnClickListener {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
        imageButton13.setOnClickListener {
            val intent = Intent(this, Subjects::class.java)
            startActivity(intent)
        }
        imageButton12.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
    }}
