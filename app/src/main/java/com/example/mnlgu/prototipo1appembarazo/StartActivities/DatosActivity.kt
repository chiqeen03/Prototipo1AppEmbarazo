package com.example.mnlgu.prototipo1appembarazo.StartActivities

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.mnlgu.prototipo1appembarazo.R
import com.example.mnlgu.prototipo1appembarazo.Tabs.MainTabsActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_datos.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_main.*

class DatosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos)

        //database
        val db : FirebaseFirestore = FirebaseFirestore.getInstance() //database

        //HashMap Auxiliar
        var user : MutableMap<String, Any> = HashMap()

        //escondemos la action bar
        supportActionBar?.hide()

        //variables para el HashMap
        var nombre: String
        var correo: String
        var contrasenia: String
        var confirmarContrasenia: String
        var peso: String
        var estatura: String
        var regla: String
        var semanaGestacion: String

        //resetea el color a negro
        confirmarContraseñaText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                confirmarContraseñaText.setTextColor(Color.BLACK)
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                confirmarContraseñaText.setTextColor(Color.BLACK)
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                confirmarContraseñaText.setTextColor(Color.BLACK)
            }
        })

        //checa cuando el boton se clickea
        crearUsuarioButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {

                //se llenan las variables del hashmap para su subida a base de datos
                nombre = nameText.text.toString()
                correo = correoText.text.toString()
                contrasenia = contraseñaText.text.toString()
                confirmarContrasenia = confirmarContraseñaText.text.toString()
                peso = pesoText.text.toString()
                estatura = estaturaText.text.toString()
                regla = reglaText.text.toString()
                semanaGestacion = semanaText.text.toString()

                //booleanos auxiliares
                var notEmpty : Boolean = checkAll(nombre, correo, contrasenia, confirmarContrasenia, peso, estatura, regla, semanaGestacion)
                var checkPass : Boolean = checkPassword(contrasenia, confirmarContrasenia)

                //si no esta vacio ningun campo, y las contraseñas coinciden se llena el hashmap y se sube a la db
                if (notEmpty && checkPass){
                    user.put("nombre", nombre)
                    user.put("correo", correo)
                    user.put("contraseña", contrasenia)
                    //user.put("confirmarContraseña", confirmarContrasenia)
                    user.put("peso", peso.toFloat())
                    user.put("estatura", estatura.toFloat())
                    user.put("regla", regla)
                    user.put("semanaGestacion", semanaGestacion.toInt())

                    //se sube a la db
                    db.collection("users").add(user)

                    //inicia la siguiente actividad
                    val intent = Intent(this@DatosActivity, LoginActivity::class.java)
                    startActivity(intent)
                }
                //si esta vacio manda mensaje de que se llenen todos los campos
                else if (!notEmpty){
                    Toast.makeText(applicationContext, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show()
                }
                //si las contraseñas no coinciden se manda mensaje y se pone texto en rojo
                else if (!checkPass){
                    confirmarContraseñaText.setTextColor(Color.RED)
                    Toast.makeText(applicationContext, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    //checa si todos estan llenos
    fun checkAll(nombre: String, correo: String, contrasenia: String, confirmarContrasenia: String, peso: String,
                 estatura: String, regla: String, semanaGestacion: String) : Boolean{
        if(nombre.trim().length>0 && correo.trim().length>0 && contrasenia.trim().length>0 && confirmarContrasenia.trim().length>0
            && peso.trim().length>0 && estatura.trim().length>0 && regla.trim().length>0 && semanaGestacion.trim().length>0)
            return true
        return false
    }

    //checa si las contraseñas son iguales
    fun checkPassword(contrasenia: String, confirmarContrasenia: String) : Boolean{
        if(contrasenia.equals(confirmarContrasenia))
            return true
        return false
    }
}
