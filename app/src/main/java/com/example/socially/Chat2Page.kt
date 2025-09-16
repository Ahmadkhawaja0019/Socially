package com.example.socially

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Chat2Page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat2_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val callButton = findViewById<ImageButton>(R.id.ic_call)

        val dpUriString = intent.getStringExtra("dpUri")
        val receivedUsername = intent.getStringExtra("username")

        backButton.setOnClickListener {
            val intent = Intent(this@Chat2Page, DirectMessage::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            intent.putExtra("dpUri", dpUriString)
            intent.putExtra("username", receivedUsername)
            startActivity(intent)
            finish()
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(this@Chat2Page, DirectMessage::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                }
                intent.putExtra("dpUri", dpUriString)
                intent.putExtra("username", receivedUsername)
                startActivity(intent)
                finish()
            }
        })
        callButton.setOnClickListener {
            val intent = Intent(this@Chat2Page, CallPageChat2::class.java)
            intent.putExtra("dpUri", dpUriString)
            intent.putExtra("username", receivedUsername)
            startActivity(intent)
        }
    }
}