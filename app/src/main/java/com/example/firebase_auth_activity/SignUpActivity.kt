package com.example.firebase_auth_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = Firebase.auth

        btn_SignUp.setOnClickListener {

            if (UserEmail.text.toString().isNotEmpty() && UserPassword.text.toString().isNotEmpty()){

                createNewAccount(UserEmail.text.toString() , UserPassword.text.toString())

            }else{

                Toast.makeText(this , "You must fill in your data first" , Toast.LENGTH_SHORT).show()
            }
        }

        tv_signIn.setOnClickListener {

            val i = Intent(this, SignInActivity::class.java)
            startActivity(i)
        }

    }


    private fun createNewAccount(email: String , password: String){

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->

            if (task.isSuccessful){
                val  user = auth.currentUser
                Log.d("Falasteen Auth", "createUserWithEmail:success")
                Toast.makeText(this , "successfully registered" , Toast.LENGTH_SHORT).show()
                updateUI()

            }else{
                Log.d("Falasteen Auth", "createUserWithEmail:failure")
                Toast.makeText(this , "Registration failed" , Toast.LENGTH_SHORT).show()
            }

        }
    }


    fun updateUI() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }


    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            updateUI()
        }

    }

}