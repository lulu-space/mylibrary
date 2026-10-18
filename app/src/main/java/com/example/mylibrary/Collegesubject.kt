package com.example.mylibrary

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class CollegeSubject : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collegesubject) // Make sure the XML layout is loaded

        // Reference the spinners by ID
        val spinnerMandatory = findViewById<Spinner>(R.id.spinnerMandatory)
        val spinnerElective = findViewById<Spinner>(R.id.spinnerElective)

        // Define the arrays of subjects
        val mandatorySubjects = resources.getStringArray(R.array.mandatory_subjects)
        val electiveRequirements = resources.getStringArray(R.array.elective_requirements)

        // Set up the adapters for the spinners
        val mandatoryAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, mandatorySubjects)
        mandatoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMandatory.adapter = mandatoryAdapter

        val electiveAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, electiveRequirements)
        electiveAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerElective.adapter = electiveAdapter

        // Set item selected listeners for the spinners
        spinnerMandatory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                // Navigate to a new activity based on the selected item
                val selectedSubject = mandatorySubjects[position]
                val intent =
                    Intent(this@CollegeSubject, getActivityClassForSubject(selectedSubject))
                startActivity(intent)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinnerElective.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                // Navigate to a new activity based on the selected item
                val selectedElective = electiveRequirements[position]
                val intent =
                    Intent(this@CollegeSubject, getActivityClassForElective(selectedElective))
                startActivity(intent)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        val imageButton13: ImageButton = findViewById(R.id.imageButton13)
        val imageButton12: ImageButton = findViewById(R.id.imageButton12)
        imageButton12.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
        imageButton13.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
    }

    private fun getActivityClassForSubject(subject: String): Class<*> {
     //Return the corresponding activity class based on the subject
     return when (subject) {
        "English", "Computer Skills","Arabic","Palestinian Studies" -> Subrecource::class.java
     else -> HomePage::class.java}}


     private fun getActivityClassForElective(elective: String): Class<*> {
    // Return the corresponding activity class based on the elective
      return when (elective) {
        "Fine Arts", "Critical Thinking","Physical Education","Islamic Culture","Hebrew Language","Turkish Language" -> Subrecource::class.java
       else -> HomePage::class.java}}


}
