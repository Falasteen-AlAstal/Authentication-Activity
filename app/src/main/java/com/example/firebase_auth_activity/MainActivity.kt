package com.example.firebase_auth_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        userInfo()

        butLogout.setOnClickListener {

            Firebase.auth.signOut()

            val i = Intent(this, SignInActivity::class.java)
            startActivity(i)
            Toast.makeText(this , "successfully singOut" , Toast.LENGTH_SHORT).show()

        }

    }


    private  fun userInfo(){

        val user = Firebase.auth.currentUser
        user?.let {

            user_email.text = it.email
            user_id.text = it.uid
        }


    }







}