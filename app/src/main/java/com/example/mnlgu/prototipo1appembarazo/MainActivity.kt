package com.example.mnlgu.prototipo1appembarazo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        var DietaBttn = findViewById<Button>(R.id.Dietabttn)
        DietaBttn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?){
                val intent = Intent(this@MainActivity, Dieta::class.java)
                startActivity(intent)
            }
        })

        var EjerciciosButton = findViewById<Button>(R.id.EjerciciosButton)
        EjerciciosButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?){
                val intent = Intent(this@MainActivity, Ejercicios::class.java)
                startActivity(intent)
            }
        })
    }
}
