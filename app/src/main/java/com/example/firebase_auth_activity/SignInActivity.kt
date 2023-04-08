package com.example.firebase_auth_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_in.UserEmail
import kotlinx.android.synthetic.main.activity_sign_in.UserPassword
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        auth = Firebase.auth


        btn_SignIn.setOnClickListener {

            if (UserEmail.text.toString().isNotEmpty() && UserPassword.text.toString().isNotEmpty()){

                signInAccount(UserEmail.text.toString() , UserPassword.text.toString())

            }else{

                Toast.makeText(this , "You must fill in your data first" , Toast.LENGTH_SHORT).show()
            }
        }


        tv_signUp.setOnClickListener {
            val i = Intent(this, SignUpActivity::class.java)
            startActivity(i)

        }
    }




    private fun signInAccount(email: String , password: String){

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    Log.e("Falasteen Auth", "signInWithEmail:success")
                    Toast.makeText(this , "successfully singIn" , Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI()
                } else {

                    Log.e("Falasteen Auth", "signInWithEmail:failure", task.exception)
                    Toast.makeText(this , "singIn failed" , Toast.LENGTH_SHORT).show()

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












