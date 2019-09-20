package com.tk.loginapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_dash_board_page.*

class DashBoardPage : AppCompatActivity() {
    var emailRegex= Regex("[a-zA-Z0-9]{2,}@[a-zA-Z]{2,}.com")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board_page)

        var sharedPreferences = getSharedPreferences("Models", Context.MODE_PRIVATE)
        var userDetails = sharedPreferences.getStringSet("display", hashSetOf())




        for (i in 0..2){
            if (userDetails?.elementAt(i)?.matches(emailRegex) as Boolean){
                val email = userDetails.elementAt(i)
                emailDisplay.text = "Your Email Is $email"
                break
            }

        }
        for (i in 0..2){
            if (!(userDetails?.elementAt(i)?.contains("password"))!! && !(userDetails.elementAt(i)?.matches(emailRegex) as Boolean)){
                val username = userDetails.elementAt(i)
                usernameDispay.text = "Your Username is $username"
            }
        }




    }


    fun logout(view: View){
        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        Toast.makeText(this,"Logout Successfull!!", Toast.LENGTH_SHORT).show()
    }
}
