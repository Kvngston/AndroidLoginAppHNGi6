package com.tk.loginapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register_page.*

class RegisterPage : AppCompatActivity() {

    var emailRegex= Regex("[a-zA-Z0-9]{2,}@[a-zA-Z]{2,}.com")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        registerBtn.setOnClickListener{
            register()
        }

    }

    private fun register(){
        var intent = Intent(this,MainActivity::class.java)
        var sharedPreferences = getSharedPreferences("Models", Context.MODE_PRIVATE)
        var editor:SharedPreferences.Editor = sharedPreferences.edit()
        var user = sharedPreferences.getStringSet("${username.text}", setOf<String>())



        //for the Email
        if (!email.text.toString().matches(emailRegex)) {
            emailX.visibility = View.VISIBLE
            email.setText("")
            email.hint = "Invalid Email"
        }else
            emailX.visibility = View.INVISIBLE



        //For the Username

        if (user?.isEmpty() as Boolean)
            usernameX.visibility = View.INVISIBLE
        else {
            println(user.toString())
            usernameX.visibility = View.VISIBLE
            username.setText("")
            username.hint = "Username not Available"
        }


        //for the Password
        if (password.text.toString().length < 8) {
            passwordX.visibility = View.VISIBLE
            Toast.makeText(this, "Password should be more than 8 characters", Toast.LENGTH_SHORT).show()
        }else
            passwordX.visibility = View.INVISIBLE

        if (password.text.toString() == confPassTxt.text.toString())
            confPassX.visibility = View.INVISIBLE
        else {
            confPassX.visibility = View.VISIBLE
            confPassTxt.setText("")
            confPassTxt.hint = "Passwords don't Match"
        }


        if(emailX.visibility != View.VISIBLE && usernameX.visibility != View.VISIBLE && passwordX.visibility != View.VISIBLE && confPassX.visibility != View.VISIBLE){

            var userDetails = hashSetOf("${email.text}","${username.text}","${password.text} password")
            editor.putStringSet("${username.text}",userDetails)
            editor.putStringSet("${email.text}", userDetails)
            editor.commit()

            Toast.makeText(this,"Registered!!",Toast.LENGTH_SHORT).show()
            startActivity(intent)

        }






    }
}