package com.example.mnlgu.prototipo1appembarazo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        var emailTextView = findViewById<TextView>(R.id.emailText)
        emailTextView.setHint("E-mail")

        var passwordTextView = findViewById<TextView>(R.id.passwordText)
        passwordTextView.setHint("Password")

        var continueButton = findViewById<Button>(R.id.continueButton)
        continueButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {

                if(emailTextView.text.toString().equals("admin") && passwordTextView.text.toString().equals("password")){
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this@LoginActivity, "Incorrect user/password", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }
}
