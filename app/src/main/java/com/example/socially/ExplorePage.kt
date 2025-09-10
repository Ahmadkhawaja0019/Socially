package com.example.socially

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import de.hdodenhof.circleimageview.CircleImageView

class ExplorePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_explore_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val home = findViewById<ImageButton>(R.id.nav_home)
        val dp = findViewById<CircleImageView>(R.id.profile_pic_bottom)
        val searchBar = findViewById<TextView>(R.id.searchBar)

        val receivedUsername = intent.getStringExtra("username")
        val dpUriString = intent.getStringExtra("dpUri")

        dpUriString?.let {
            dp.setImageURI(android.net.Uri.parse(it))
        }

        home.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            intent.putExtra("dpUri", dpUriString)
            intent.putExtra("username", receivedUsername)
            startActivity(intent)
        }

        searchBar.setOnClickListener {
            val intent = Intent(this, SearchPage::class.java)
            intent.putExtra("username", receivedUsername)
            startActivity(intent)
        }
    }
}