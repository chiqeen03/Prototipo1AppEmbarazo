package com.example.mnlgu.prototipo1appembarazo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class DatosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos)

        supportActionBar?.hide()

        var crearButton = findViewById<Button>(R.id.crearUsuarioButton)
        crearButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent(this@DatosActivity, MainActivity::class.java)
                startActivity(intent)
            }
        })
    }
}
