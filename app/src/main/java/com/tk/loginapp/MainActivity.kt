package com.tk.loginapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var emailRegex= Regex("[a-zA-Z0-9]{2,}@[a-zA-Z]{2,}.com")
//    public var loggedInUser = setOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var registerIntent = Intent(this, RegisterPage::class.java)



        registerTxt.setOnClickListener{
            startActivity(registerIntent)
        }

        loginBtn.setOnClickListener{
            login()
        }
    }



    fun login(){
        var sharedPreferences = getSharedPreferences("Models", Context.MODE_PRIVATE)
        var dashBoardIntent = Intent(this,DashBoardPage::class.java)
        var loggedInUser:HashSet<String>

        try {
            var userDetails = sharedPreferences.getStringSet("${emailOrUsername.text}", setOf())
            var editor:SharedPreferences.Editor = sharedPreferences.edit()

            if (userDetails != null) {
                if (!userDetails.contains(emailOrUsername.text.toString())) {
                    password.setText("")
                }

                if (userDetails.contains(password.text.toString() + " password")) {
                    Toast.makeText(this, "Login Successful!!", Toast.LENGTH_SHORT).show()
                    editor.putStringSet("display", userDetails)
                    editor.commit()
                    startActivity(dashBoardIntent)
                } else {
                    Toast.makeText(this, "Invalid Details", Toast.LENGTH_SHORT).show()
                }
            }
        }catch (e:Exception){
            System.err.println(e.message)
        }

    }

}
