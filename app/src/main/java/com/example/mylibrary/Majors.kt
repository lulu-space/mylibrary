package com.example.mylibrary
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Majors : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_majors)

        val buttonCyberSecurity: Button = findViewById(R.id.button_cyber_security)
        val buttonMultimedia: Button = findViewById(R.id.button_multimedia)
        val buttonDiploma: Button = findViewById(R.id.button_diploma)
        val buttonComputerScience: Button = findViewById(R.id.button_computer_science)
        val imageButton13: ImageButton = findViewById(R.id.imageButton13)
        val imageButton12: ImageButton = findViewById(R.id.imageButton12)
        // يمكنك إضافة وظائف للأزرار هنا إذا كنت بحاجة إلى ذلك
        buttonCyberSecurity.setOnClickListener {
            val intent = Intent(this, Subjects::class.java)
            startActivity(intent)
        }

        buttonMultimedia.setOnClickListener {
            val intent = Intent(this, Subjects::class.java)
            startActivity(intent)        }

        buttonDiploma.setOnClickListener {
            val intent = Intent(this, Subjects::class.java)
            startActivity(intent)        }

        buttonComputerScience.setOnClickListener {
            val intent = Intent(this, Subjects::class.java)
            startActivity(intent)        }

        imageButton13.setOnClickListener {
            val intent = Intent(this, Colleges::class.java)
            startActivity(intent)
        }
        imageButton12.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
    }

}
