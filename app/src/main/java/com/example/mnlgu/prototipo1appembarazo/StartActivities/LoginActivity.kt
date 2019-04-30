package com.example.mnlgu.prototipo1appembarazo.StartActivities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mnlgu.prototipo1appembarazo.R
import com.example.mnlgu.prototipo1appembarazo.R.id.continueButton
import com.example.mnlgu.prototipo1appembarazo.R.id.emailText
import com.example.mnlgu.prototipo1appembarazo.Tabs.MainTabsActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        continueButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                //cambiar aca para que acepte lo de la base de datos
                //------------------------------------------------------------------------------------------------------
                if(emailText.text.toString().equals("admin") && passwordText.text.toString().equals("password")){
                    val intent = Intent(this@LoginActivity, MainTabsActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this@LoginActivity, "Incorrect user/password", Toast.LENGTH_SHORT).show()
                }
                //------------------------------------------------------------------------------------------------------
            }
        })

        newUser.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent(this@LoginActivity, DatosActivity::class.java)
                startActivity(intent)
            }
        })
    }
}
