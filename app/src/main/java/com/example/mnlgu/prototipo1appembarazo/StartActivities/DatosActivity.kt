package com.example.mnlgu.prototipo1appembarazo.StartActivities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.mnlgu.prototipo1appembarazo.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_datos.*
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuthUserCollisionException


class DatosActivity : AppCompatActivity(){

    val warningColor = "#ffcece"
    val mAuth = FirebaseAuth.getInstance()

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
                var checkEmail: Boolean = checkEmail(correo)

                //si no esta vacio ningun campo, y las contraseñas coinciden se llena el hashmap y se sube a la db
                if (notEmpty && checkPass && passLength && checkEmail){
                    user.put("nombre", nombre)
                    user.put("correo", correo) // para authentication
                    //user.put("contraseña", contrasenia) // para authentication
                    //user.put("confirmarContraseña", confirmarContrasenia)
                    user.put("peso", peso.toFloat())
                    user.put("estatura", estatura.toFloat())
                    user.put("regla", regla)
                    user.put("semanaGestacion", semanaGestacion.toInt())

                    progressBar.visibility = View.VISIBLE

                    mAuth.createUserWithEmailAndPassword(correo, contrasenia).addOnCompleteListener{
                        task: Task<AuthResult> ->
                        if(task.isSuccessful){
                            //registration OK
                            val user = mAuth.currentUser!!
                            //se sube a la db
                            db.collection("users")
                                .add(user)
                                // si es exitoso
                                .addOnSuccessListener {
                                    progressBar.visibility = View.GONE
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
                        else{
                            // error
                            if(task.exception is FirebaseAuthUserCollisionException)
                                Toast.makeText(applicationContext, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                            else
                                Toast.makeText(applicationContext, "Hubo un problema con el servidor, intenta mas tarde", Toast.LENGTH_SHORT).show()
                        }
                    }

                    progressBar.visibility = View.GONE
                }
                //si alguno esta vacio manda mensaje de que se llenen todos los campos
                else if (!notEmpty){
                    Toast.makeText(applicationContext, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show()

                    //pone color rolo
                    requestFocusAll(nombre, correo, contrasenia, confirmarContrasenia, peso, estatura, regla, semanaGestacion)
                }
                else if(!checkEmail){
                    correoText.requestFocus()
                    Toast.makeText(applicationContext, "El correo no es valido", Toast.LENGTH_SHORT).show()
                }
                //si la contraseña no es lo suficientemente larga manda warning
                else if(!passLength){
                    contraseñaText.requestFocus()
                    Toast.makeText(applicationContext, "La contraseña debe ser entre 6 y 12 caracteres", Toast.LENGTH_SHORT).show()
                }
                //si las contraseñas no coinciden se manda mensaje y se pone texto en rojo
                else if (!checkPass){
                    confirmarContraseñaText.requestFocus()
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
    fun requestFocusAll(nombre: String, correo: String, contrasenia: String, confirmarContrasenia: String, peso: String,
                        estatura: String, regla: String, semanaGestacion: String){
        if(nombre.trim().length==0){
            nameText.requestFocus()
        }
        if(correo.trim().length==0){
            correoText.requestFocus()
        }
        if(contrasenia.trim().length==0){
            contraseñaText.requestFocus()
        }
        if(confirmarContrasenia.trim().length==0){
            confirmarContraseñaText.requestFocus()
        }
        if(peso.trim().length==0){
            pesoText.requestFocus()
        }
        if(estatura.trim().length==0){
            estaturaText.requestFocus()
        }
        if(regla.trim().length==0){
            reglaText.requestFocus()
        }
        if(semanaGestacion.trim().length==0){
            semanaText.requestFocus()
        }
    }

    fun checkEmail(email: String): Boolean {
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return true
        return false
    }
}


//FALTA: darle formato DATE a la regla
