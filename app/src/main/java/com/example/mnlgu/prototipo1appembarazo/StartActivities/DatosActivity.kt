package com.example.mnlgu.prototipo1appembarazo.StartActivities

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.DrawableWrapper
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.mnlgu.prototipo1appembarazo.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_datos.*


class DatosActivity : AppCompatActivity() {

    val warningColor = "#ffcece"

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

        //resetea a los colores originales cuando el texto cambia
        checkTextChanged()

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
                var passLength: Boolean = checkPasswordLength(contrasenia)

                //si no esta vacio ningun campo, y las contraseñas coinciden se llena el hashmap y se sube a la db
                if (notEmpty && checkPass && passLength){
                    user.put("nombre", nombre)
                    user.put("correo", correo)
                    user.put("contraseña", contrasenia)
                    //user.put("confirmarContraseña", confirmarContrasenia)
                    user.put("peso", peso.toFloat())
                    user.put("estatura", estatura.toFloat())
                    user.put("regla", regla)
                    user.put("semanaGestacion", semanaGestacion.toInt())

                    //se sube a la db
                    db.collection("users")
                        .add(user)
                            // si es exitoso
                        .addOnSuccessListener {
                            Toast.makeText(applicationContext, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show()
                            //inicia la siguiente actividad
                            val intent = Intent(this@DatosActivity, LoginActivity::class.java)
                            startActivity(intent)
                        }
                            //si no es exitoso
                        .addOnFailureListener {
                            Toast.makeText(applicationContext, "Hay un problema con el servidor. Intenta mas tarde", Toast.LENGTH_SHORT).show()
                        }
                }
                //si alguno esta vacio manda mensaje de que se llenen todos los campos
                else if (!notEmpty){
                    Toast.makeText(applicationContext, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show()

                    //pone color rolo
                    setWarningColors(nombre, correo, contrasenia, confirmarContrasenia, peso, estatura, regla, semanaGestacion)
                }
                //si la contraseña no es lo suficientemente larga manda warning
                else if(!passLength){
                    contraseñaText.setBackgroundColor(Color.parseColor(warningColor))
                    Toast.makeText(applicationContext, "La contraseña debe ser entre 6 y 12 caracteres", Toast.LENGTH_SHORT).show()
                }
                //si las contraseñas no coinciden se manda mensaje y se pone texto en rojo
                else if (!checkPass){
                    confirmarContraseñaText.setBackgroundColor(Color.parseColor(warningColor))
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

    //checa que las contraseñas entren en el rango
    fun checkPasswordLength(contrasenia: String): Boolean{
        if(contrasenia.length>6 && contrasenia.length<12)
            return true
        return false
    }

    //si algo esta vacio lo pone en rojo
    fun setWarningColors(nombre: String, correo: String, contrasenia: String, confirmarContrasenia: String, peso: String,
                         estatura: String, regla: String, semanaGestacion: String){
        if(nombre.trim().length==0){
            nameText.setBackgroundColor(Color.parseColor(warningColor))
        }
        if(correo.trim().length==0){
            correoText.setBackgroundColor(Color.parseColor(warningColor))
        }
        if(contrasenia.trim().length==0){
            contraseñaText.setBackgroundColor(Color.parseColor(warningColor))
        }
        if(confirmarContrasenia.trim().length==0){
            confirmarContraseñaText.setBackgroundColor(Color.parseColor(warningColor))
        }
        if(peso.trim().length==0){
            pesoText.setBackgroundColor(Color.parseColor(warningColor))
        }
        if(estatura.trim().length==0){
            estaturaText.setBackgroundColor(Color.parseColor(warningColor))
        }
        if(regla.trim().length==0){
            reglaText.setBackgroundColor(Color.parseColor(warningColor))
        }
        if(semanaGestacion.trim().length==0){
            semanaText.setBackgroundColor(Color.parseColor(warningColor))
        }
    }

    //si el texto cambia lo resetea a su background original
    fun checkTextChanged(){
        //backgrounds originales
        val nombreOldBackground: Drawable = nameText.background
        val correoOldBackground: Drawable = correoText.background
        val contraseniaOldBackground: Drawable = contraseñaText.background
        val confirmarContraseniaOldBackground: Drawable = confirmarContraseñaText.background
        val pesoOldBackground: Drawable = pesoText.background
        val estaturaOldBackground: Drawable = estaturaText.background
        val reglaOldBackground: Drawable = reglaText.background
        val semanaGestacionOldBackground: Drawable = semanaText.background

        //resetea el color del background
        nameText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                nameText.background = nombreOldBackground
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        correoText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                correoText.background = correoOldBackground
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        contraseñaText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                contraseñaText.background = contraseniaOldBackground
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        confirmarContraseñaText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                confirmarContraseñaText.background = confirmarContraseniaOldBackground
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        pesoText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                pesoText.background = pesoOldBackground
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        estaturaText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                estaturaText.background = estaturaOldBackground
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        reglaText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                reglaText.background = reglaOldBackground
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        semanaText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                semanaText.background = semanaGestacionOldBackground
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }
}
