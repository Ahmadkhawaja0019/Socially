package com.example.socially

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import de.hdodenhof.circleimageview.CircleImageView

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dp1 = findViewById<CircleImageView>(R.id.story_1)
        val dp2 = findViewById<CircleImageView>(R.id.profile_pic2)
        val exploreButton = findViewById<ImageButton>(R.id.nav_search)
        val dm = findViewById<ImageButton>(R.id.inbox)
        val notifications = findViewById<ImageButton>(R.id.nav_heart)

        val dpUriString = intent.getStringExtra("dpUri")
        val receivedUsername = intent.getStringExtra("username")

        dpUriString?.let {
            dp1.setImageURI(android.net.Uri.parse(it))
        }
        dpUriString?.let {
            dp2.setImageURI(android.net.Uri.parse(it))
        }

        exploreButton.setOnClickListener {
            val intent = Intent(this, ExplorePage::class.java)
            intent.putExtra("dpUri", dpUriString)
            intent.putExtra("username", receivedUsername)
            startActivity(intent)
        }
        dm.setOnClickListener {
            val intent = Intent(this, DirectMessage::class.java)
            intent.putExtra("username", receivedUsername)
            startActivity(intent)
        }
        notifications.setOnClickListener {
            val intent = Intent(this, NotificationsPage1::class.java)
            startActivity(intent)
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAffinity()
            }
        })
    }
}