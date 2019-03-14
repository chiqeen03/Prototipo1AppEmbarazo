package com.example.mnlgu.prototipo1appembarazo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Dieta : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dieta)

        var RegresaBttn = findViewById<Button>(R.id.Dieta_Regresar_button)
        RegresaBttn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?){
                val intent = Intent(this@Dieta, MainActivity::class.java)
                startActivity(intent)
            }
        })
    }
}
