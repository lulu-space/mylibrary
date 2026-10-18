package com.example.mylibrary

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)

        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize buttons and text views
        val popupimage: ImageButton = findViewById(R.id.popupImage)
        val imageButton4: ImageButton = findViewById(R.id.imageButton4)
        val imageButton5: ImageButton = findViewById(R.id.imageButton5)
        val imageButton3: ImageButton = findViewById(R.id.imageButton3)


        val textView: TextView = findViewById(R.id.textView)
        val textView2: TextView = findViewById(R.id.textView2)
        val textView3: TextView = findViewById(R.id.textView3)

        // Set click listeners for image buttons
        popupimage.setOnClickListener { view ->
            showPopupMenu(view) // Show the popup menu when imageButton3 is clicked
        }

        imageButton4.setOnClickListener {
            Toast.makeText(this, "Navigating to Colleges", Toast.LENGTH_SHORT).show() // Log statement
            val intent = Intent(this, Colleges::class.java)
            startActivity(intent)
        }
        imageButton3.setOnClickListener {
            val intent = Intent(this, CollegeSubject::class.java)
            startActivity(intent)
        }

        imageButton5.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_item1 -> {
                    Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_item2 -> {
                    Toast.makeText(this, "Info clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_item3 -> {
                    Toast.makeText(this, "Report clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}