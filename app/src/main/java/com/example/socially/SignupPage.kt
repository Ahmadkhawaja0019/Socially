package com.example.socially

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import de.hdodenhof.circleimageview.CircleImageView

class SignupPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backButton = findViewById<android.widget.ImageButton>(R.id.back_button)
        val dp = findViewById<CircleImageView>(R.id.dp)
        val userName = findViewById<EditText>(R.id.userNameInput)
        val firstName = findViewById<EditText>(R.id.firstNameInput)
        val lastName = findViewById<EditText>(R.id.lastNameInput)
        val dateOfBirth = findViewById<EditText>(R.id.dobInput)
        val email = findViewById<EditText>(R.id.emailTextValue)
        val pass = findViewById<EditText>(R.id.passInput)
        val createAccountButton = findViewById<TextView>(R.id.createAccountButton)

        backButton.setOnClickListener {
            finishAffinity()
        }

        var selectedDpUri: android.net.Uri? = null
        var launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                var uri=it.data?.data
                selectedDpUri = uri
                dp.setImageURI(uri)
            }
        }

        dp.setOnClickListener {
            var intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            launcher.launch(intent)
        }

        createAccountButton.setOnClickListener {
            val intent = Intent(this, AskLoginPage::class.java)
            intent.putExtra("userName", userName.text.toString())
            intent.putExtra("firstName", firstName.text.toString())
            intent.putExtra("lastName", lastName.text.toString())
            intent.putExtra("dateOfBirth", dateOfBirth.text.toString())
            intent.putExtra("email", email.text.toString())
            intent.putExtra("pass", pass.text.toString())
            intent.putExtra("dpUri", selectedDpUri?.toString())
            startActivity(intent)
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAffinity()
            }
        })
    }
}