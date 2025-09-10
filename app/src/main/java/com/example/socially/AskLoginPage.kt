package com.example.socially

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import de.hdodenhof.circleimageview.CircleImageView

class AskLoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ask_login_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dp = findViewById<CircleImageView>(R.id.dp)
        val userName = findViewById<TextView>(R.id.userNameText)
        val loginButton = findViewById<TextView>(R.id.loginButton)
        val signUpButton = findViewById<TextView>(R.id.signupButton)

        val receivedUsername = intent.getStringExtra("userName") ?: ""
        val receivedPassword = intent.getStringExtra("pass") ?: ""
        val dpUriString = intent.getStringExtra("dpUri")

        userName.text = receivedUsername

        dpUriString?.let {
            dp.setImageURI(android.net.Uri.parse(it))
        }

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginPage::class.java)
            intent.putExtra("username", receivedUsername)
            intent.putExtra("password", receivedPassword)
            intent.putExtra("dpUri", dpUriString)
            startActivity(intent)
        }

        signUpButton.setOnClickListener {
            val intent = Intent(this, SignupPage::class.java)
            startActivity(intent)
            finish()
        }
    }
}