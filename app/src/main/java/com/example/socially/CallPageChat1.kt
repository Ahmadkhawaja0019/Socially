package com.example.socially

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CallPageChat1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_call_page_chat1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val endCall = findViewById<ImageButton>(R.id.endCallButton)

        val dpUriString = intent.getStringExtra("dpUri")
        val receivedUsername = intent.getStringExtra("username")

        endCall.setOnClickListener {
            val intent = Intent(this@CallPageChat1, Chat1Page::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            intent.putExtra("dpUri", dpUriString)
            intent.putExtra("username", receivedUsername)
            startActivity(intent)
            finish()
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(this@CallPageChat1, Chat1Page::class.java).apply {
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