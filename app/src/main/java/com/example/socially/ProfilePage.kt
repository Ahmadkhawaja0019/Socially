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
import de.hdodenhof.circleimageview.CircleImageView

class ProfilePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val editProfile = findViewById<TextView>(R.id.editprofile)
        val highlightsBtn = findViewById<LinearLayout>(R.id.friendsHighlights)
        val home = findViewById<ImageButton>(R.id.nav_home)
        val dp1 = findViewById<CircleImageView>(R.id.dp1)
        val dp2 = findViewById<CircleImageView>(R.id.dp2)
        val exploreButton = findViewById<ImageButton>(R.id.nav_search)
        val createPost = findViewById<ImageButton>(R.id.nav_add)
        val notifications = findViewById<ImageButton>(R.id.nav_heart)


        val dpUriString = intent.getStringExtra("dpUri")
        val receivedUsername = intent.getStringExtra("username")

        dpUriString?.let {
            dp1.setImageURI(android.net.Uri.parse(it))
        }
        dpUriString?.let {
            dp2.setImageURI(android.net.Uri.parse(it))
        }
        highlightsBtn.setOnClickListener {
            val intent = Intent(this, FriendsHeighlights::class.java)
            intent.putExtra("dpUri", dpUriString)
            intent.putExtra("username", receivedUsername)
            startActivity(intent)
        }
        editProfile.setOnClickListener {
            val intent = Intent(this, EditProfilePage::class.java)
            intent.putExtra("dpUri", dpUriString)
            intent.putExtra("username", receivedUsername)
            startActivity(intent)
        }
        home.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            intent.putExtra("dpUri", dpUriString)
            intent.putExtra("username", receivedUsername)
            startActivity(intent)
        }
        exploreButton.setOnClickListener {
            val intent = Intent(this, ExplorePage::class.java)
            intent.putExtra("dpUri", dpUriString)
            intent.putExtra("username", receivedUsername)
            startActivity(intent)
        }
        notifications.setOnClickListener {
            val intent = Intent(this, NotificationsPage1::class.java)
            intent.putExtra("dpUri", dpUriString)
            intent.putExtra("username", receivedUsername)
            startActivity(intent)
        }

        createPost.setOnClickListener {
            val intent = Intent(this, CreatePostPage::class.java)
            intent.putExtra("dpUri", dpUriString)
            intent.putExtra("username", receivedUsername)
            startActivity(intent)
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(this@ProfilePage, HomePage::class.java).apply {
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