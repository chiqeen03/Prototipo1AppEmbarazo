package com.example.mnlgu.prototipo1appembarazo.StartActivities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mnlgu.prototipo1appembarazo.R
import com.example.mnlgu.prototipo1appembarazo.Tabs.MainTabsActivity

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
                    val intent = Intent(this@LoginActivity, MainTabsActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this@LoginActivity, "Incorrect user/password", Toast.LENGTH_SHORT).show()
                }

            }
        })

        var newUserButton = findViewById<Button>(R.id.newUser)
        newUserButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent(this@LoginActivity, DatosActivity::class.java)
                startActivity(intent)
            }
        })
    }
}
