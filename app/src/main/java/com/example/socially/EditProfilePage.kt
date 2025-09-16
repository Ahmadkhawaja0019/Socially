package com.example.socially

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import de.hdodenhof.circleimageview.CircleImageView

class EditProfilePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_profile_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val cancelButton = findViewById<TextView>(R.id.cancel_button)
        val doneButton = findViewById<TextView>(R.id.done)
        val dp = findViewById<CircleImageView>(R.id.dp)

        val dpUriString = intent.getStringExtra("dpUri")
        val receivedUsername = intent.getStringExtra("username")

        dpUriString?.let {
            dp.setImageURI(android.net.Uri.parse(it))
        }

        cancelButton.setOnClickListener {
            finish()
        }
        doneButton.setOnClickListener {

            finish()
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }
}