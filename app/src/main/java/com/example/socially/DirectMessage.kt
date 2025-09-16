package com.example.socially

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DirectMessage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_direct_message)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val chat1 = findViewById<LinearLayout>(R.id.chat1message)
        val chat2 = findViewById<LinearLayout>(R.id.chat2message)
        val chat3 = findViewById<LinearLayout>(R.id.chat3message)
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val userName = findViewById<TextView>(R.id.username)

        val dpUriString = intent.getStringExtra("dpUri")
        val receivedUsername = intent.getStringExtra("username") ?: ""
        userName.text = if (!receivedUsername.isNullOrEmpty()) {
            receivedUsername
        } else {
            getString(R.string.user_name)
        }

        backButton.setOnClickListener {
            val intent = Intent(this@DirectMessage, HomePage::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            intent.putExtra("dpUri", dpUriString)
            intent.putExtra("username", receivedUsername)
            startActivity(intent)
            finish()
        }

        chat1.setOnClickListener {
            val intent = android.content.Intent(this, Chat1Page::class.java)
            intent.putExtra("dpUri", dpUriString)
            intent.putExtra("username", receivedUsername)
            startActivity(intent)
        }
        chat2.setOnClickListener {
            val intent = android.content.Intent(this, Chat2Page::class.java)
            intent.putExtra("dpUri", dpUriString)
            intent.putExtra("username", receivedUsername)
            startActivity(intent)
        }
        chat3.setOnClickListener {
            val intent = android.content.Intent(this, Chat3Page::class.java)
            intent.putExtra("dpUri", dpUriString)
            intent.putExtra("username", receivedUsername)
            startActivity(intent)
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(this@DirectMessage, HomePage::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                }
                intent.putExtra("dpUri", dpUriString)
                intent.putExtra("username", receivedUsername)
                startActivity(intent)
                finish()
            }
        })
    }
}