package com.example.mylibrary

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.LinearLayout

class Subjects : AppCompatActivity() {

    private lateinit var searchView: SearchView
    private lateinit var buttonLayout: LinearLayout // To hold all the buttons dynamically
    private lateinit var buttonNames: Array<String> // Array of course names

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_subjects)

        // Initialize the LinearLayout where buttons are placed
        buttonLayout = findViewById(R.id.buttonLayout)

        // Log the buttonLayout reference to check if it's null or not
        Log.d("CollegesActivity", "Button Layout: $buttonLayout")

        // Array of course button names
        buttonNames = arrayOf(
            "INTRODUCTION TO INFORMATION TECHNOLOGY LAB",
            "COMPUTER NETWORKS LAB",
            "DIGITAL LOGIC DESIGN",
            "PROGRAMMING FUNDAMENTALS II LAB",
            "DATA STRUCTURE LAB",
            "PROGRAMMING FUNDAMENTALS II",
            "DATA STRUCTURES",
            "INTRODUCTION TO COMPUTER ORGANIZATION",
            "ALGORITHMS AND PROGRAMMING TECHNIQUES",
            "INTRODUCTION TO DATABASE",
            "INTRODUCTION TO DATABASE (LAB)",
            "INTRODUCTION TO SOFTWARE ENGINEERING",
            "INFORMATION SECURITY",
            "MOBILE PROGRAMMING",
            "INTRODUCTION TO OPERATING SYSTEMS",
            "SOFTWARE TESTING AND VALIDATION",
            "INTERNSHIP",
            "COMPUTER SCIENCE FOUNDATION",
            "PROGRAMMING LANGUAGES",
            "DATA ANALYSIS",
            "SOFTWARE DEVELOPMENT",
            "WEB DEVELOPMENT",
            "MACHINE LEARNING",
            "COMPUTER GRAPHICS",
            "NETWORK SECURITY",
            "CLOUD COMPUTING",
            "ARTIFICIAL INTELLIGENCE",
            "VIRTUALIZATION",
            "BIG DATA",
            "MOBILE APP DEVELOPMENT",
            "DATABASE MANAGEMENT SYSTEMS",
            "OPERATING SYSTEMS",
            "ADVANCED ALGORITHMS",
            "ADVANCED PROGRAMMING",
            "DATA MINING"
        )

        // Dynamically create and add buttons for each course name
        buttonNames.forEach { courseName ->
            val button = Button(this)
            button.text = courseName
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                70.dpToPx() // Convert dp to px for height
            )
            button.setBackgroundColor(resources.getColor(android.R.color.holo_red_dark))
            button.textSize = 18f
            button.setOnClickListener {
                // Action for the button click (for now, just show a toast)
                Toast.makeText(this, "$courseName clicked", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Subrecource::class.java)
                startActivity(intent)
            }
            buttonLayout.addView(button)
        }

        // Initialize the SearchView
        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterButtons(newText)
                return true
            }
        })

        // Edge-to-edge insets handling
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val imageButton13: ImageButton = findViewById(R.id.imageButton13)
        val imageButton12: ImageButton = findViewById(R.id.imageButton12)

        imageButton13.setOnClickListener {
            val intent = Intent(this, Colleges::class.java)
            startActivity(intent)
        }
        imageButton12.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

    }

    private fun filterButtons(query: String?) {
        buttonLayout.removeAllViews()  // Remove existing buttons
        buttonNames.filter { it.contains(query ?: "", ignoreCase = true) }.forEach { courseName ->
            val button = Button(this)
            button.text = courseName
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                70.dpToPx()
            )
            button.setBackgroundColor(resources.getColor(android.R.color.holo_red_dark))
            button.textSize = 18f
            button.setOnClickListener {
                // Action for the button click (for now, just show a toast)
                Toast.makeText(this, "$courseName clicked", Toast.LENGTH_SHORT).show()
            }
            buttonLayout.addView(button)
        }
    }

    private fun Int.dpToPx(): Int {
        return (this * resources.displayMetrics.density).toInt()
    }

}
