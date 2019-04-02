package com.example.mnlgu.prototipo1appembarazo.StartActivities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.mnlgu.prototipo1appembarazo.R
import com.example.mnlgu.prototipo1appembarazo.Tabs.MainTabsActivity

class DatosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos)

        supportActionBar?.hide()

        var crearButton = findViewById<Button>(R.id.crearUsuarioButton)
        crearButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent(this@DatosActivity, MainTabsActivity::class.java)
                startActivity(intent)
            }
        })
    }
}
