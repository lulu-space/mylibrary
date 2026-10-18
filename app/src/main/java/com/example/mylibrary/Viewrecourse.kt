package com.example.mylibrary

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Viewrecourse : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_viewrecourse)

        // Apply window insets for edge-to-edge behavior
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find the ImageButton views
        val imageButton12: ImageButton = findViewById(R.id.imageButton12)
        val imageButton13: ImageButton = findViewById(R.id.imageButton13)
        val imageButton4: ImageButton = findViewById(R.id.imageButton4)
        val popupImage: ImageButton = findViewById(R.id.popupImage)

        // Set listeners for each ImageButton
        popupImage.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
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

        imageButton4.setOnClickListener {
            openLink("https://ocw.mit.edu/courses/res-18-001-calculus-fall-2023/pages/textbook/")
        }

        // Setup the popup menu
        setupPopupMenu(popupImage)
    }

    // Method to open a URL link
    fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    // Setup the popup menu for the popupImage button
    private fun setupPopupMenu(popupImage: ImageButton) {
        popupImage.setOnClickListener { view ->
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
}
