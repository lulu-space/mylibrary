package com.example.mylibrary

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Login : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextName = findViewById<EditText>(R.id.username_input)
        val editTextEmail = findViewById<EditText>(R.id.password_input)
        val btnSave = findViewById<Button>(R.id.login_btn)

        database = FirebaseDatabase.getInstance().getReference("Users")

        btnSave.setOnClickListener {
            val name = editTextName.text.toString()
            val email = editTextEmail.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty()) {
                val userId = database.push().key
                val user = User(userId, name, email)
                database.child(userId!!).setValue(user)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Data saved successfully for $name!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity2::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Unable to save data.", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Please enter all entries.", Toast.LENGTH_SHORT).show()
            }
        }

        val imageButton13: ImageButton = findViewById(R.id.imageButton13)
        val imageButton12: ImageButton = findViewById(R.id.imageButton12)

        imageButton13.setOnClickListener {
            val intent = Intent(this, Subrecource::class.java)
            startActivity(intent)
        }
        imageButton12.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
    }
}

data class User(val userId: String?, val name: String, val email: String)