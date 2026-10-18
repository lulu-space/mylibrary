package com.example.mylibrary

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
//import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // ربط الأيقونات
        val instagramIcon: ImageView = findViewById(R.id.instagramIcon)
        val facebookIcon: ImageView = findViewById(R.id.facebookIcon)
        val snapchatIcon: ImageView = findViewById(R.id.snapchatIcon)
        val youtubeIcon: ImageView = findViewById(R.id.youtubeIcon)
        val twitterIcon: ImageView = findViewById(R.id.twitterIcon)

        // إضافة listener عند الضغط على الأيقونات
        instagramIcon.setOnClickListener {
            openLink("https://www.instagram.com/aaup_edu/")
        }

        facebookIcon.setOnClickListener {
            openLink("https://www.facebook.com/aaup")
        }

        snapchatIcon.setOnClickListener {
            openLink("https://www.snapchat.com/add/aauj.pr")
        }

        youtubeIcon.setOnClickListener {
            openLink("https://www.youtube.com/channel/UCtT-T2JNoTT0jEPL73LljrQ")
        }

        twitterIcon.setOnClickListener {
            openLink("https://x.com/i/flow/single_sign_on")
        }
        val imageButton13: ImageButton = findViewById(R.id.imageButton13)

        imageButton13.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
    }

    // دالة لفتح الرابط عند الضغط
    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}

