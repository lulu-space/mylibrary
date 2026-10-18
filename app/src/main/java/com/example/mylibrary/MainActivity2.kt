package com.example.mylibrary

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File
import java.io.IOException

class MainActivity2 : AppCompatActivity() {
    private val FILE_PICKER_REQUEST_CODE = 100
    private var selectedExamType = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val uploadButton = findViewById<Button>(R.id.quizButton)
        uploadButton.setOnClickListener { handleFilePicker("Quiz") }

        val imageButton13: ImageButton = findViewById(R.id.imageButton13)
        imageButton13.setOnClickListener {
            val intent = Intent(this, Subrecource::class.java)
            startActivity(intent)
        }

        val imageButton12: ImageButton = findViewById(R.id.imageButton12)
        imageButton12.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
    }

    private fun handleFilePicker(examType: String) {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 101)
            return
        }

        selectedExamType = examType
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "application/pdf"
        startActivityForResult(Intent.createChooser(intent, "Select File"), FILE_PICKER_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                uriToFile(uri)?.let { file ->
                    uploadFileToDatabase(file, selectedExamType)
                }
            }
        }
    }

    @SuppressLint("MissingPermission") // Suppress for brevity (request permission is handled before)
    private fun uriToFile(uri: Uri): File? {
        val file = File(cacheDir, getFileName(uri))
        contentResolver.openInputStream(uri)?.use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        return file
    }

    private fun getFileName(uri: Uri): String {
        var name = ""
        contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (cursor.moveToFirst()) {
                name = cursor.getString(nameIndex)
            }
        }
        return name
    }

    private fun uploadFileToDatabase(file: File, examType: String) {
        val client = OkHttpClient()
        val requestBody = RequestBody.create("application/pdf".toMediaTypeOrNull(), file)
        val request = Request.Builder()
            .url("https://your_database_url/upload?examType=$examType") // Replace with your actual URL
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity2, "Failed to upload file: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    if (response.isSuccessful) {
                        Toast.makeText(this@MainActivity2, "File uploaded successfully!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity2, "Failed to upload file: ${response.code}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            handleFilePicker(selectedExamType)
        } else {
            Toast.makeText(this, "Permission to read external storage is required to select a file.", Toast.LENGTH_SHORT).show()
        }
    }
}