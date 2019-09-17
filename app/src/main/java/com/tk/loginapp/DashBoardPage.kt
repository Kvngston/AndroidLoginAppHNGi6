package com.tk.loginapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dash_board_page.*

class DashBoardPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board_page)

        var sharedPreferences = getSharedPreferences("Models", Context.MODE_PRIVATE)
        var userDetails = sharedPreferences.getStringSet("display", setOf())

        val email = userDetails?.elementAt(0)
        val username = userDetails?.elementAt(1)

        emailDisplay.text = "Your Email Is $email"
        usernameDispay.text = "Your Username is $username"

    }
}
