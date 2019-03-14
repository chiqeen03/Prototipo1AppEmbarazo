package com.example.mnlgu.prototipo1appembarazo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Ejercicios : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicios)

        var regresarButton = findViewById<Button>(R.id.Regresar)
        regresarButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?){
                val intent = Intent(this@Ejercicios, MainActivity::class.java)
                startActivity(intent)
            }
        })
    }

    /*var newUserButton = findViewById<Button>(R.id.newUser)
    newUserButton.setOnClickListener(object: View.OnClickListener{
        override fun onClick(p0: View?) {
            val intent = Intent(this@LoginActivity, DatosActivity::class.java)
            startActivity(intent)
        }
    })*/
}
