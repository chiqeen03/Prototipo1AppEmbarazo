package com.example.mnlgu.prototipo1appembarazo.StartActivities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.mnlgu.prototipo1appembarazo.Datos2Activity
import com.example.mnlgu.prototipo1appembarazo.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_datos.*
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuthUserCollisionException


class DatosActivity : AppCompatActivity(){

    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos)

        //escondemos la action bar
        supportActionBar?.hide()

        //variables para el HashMap
        var correo: String
        var contrasenia: String
        var confirmarContrasenia: String

        //checa cuando el boton se clickea
        continuarButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {

                //se llenan las variables del hashmap para su subida a base de datos
                correo = correoText.text.toString()
                contrasenia = contraseñaText.text.toString()
                confirmarContrasenia = confirmarContraseñaText.text.toString()

                //booleanos auxiliares
                var notEmpty : Boolean = checkAll(correo, contrasenia, confirmarContrasenia)
                var checkPass : Boolean = checkPassword(contrasenia, confirmarContrasenia)
                var passLength: Boolean = checkPasswordLength(contrasenia)
                var checkEmail: Boolean = checkEmail(correo)

                //si no esta vacio ningun campo, y las contraseñas coinciden se llena el hashmap y se sube a la db
                if (notEmpty && checkPass && passLength && checkEmail){

                    progressBar.visibility = View.VISIBLE

                    mAuth.createUserWithEmailAndPassword(correo, contrasenia).addOnCompleteListener{
                        task: Task<AuthResult> ->
                        if(task.isSuccessful){
                            //registration OK
                            val user = mAuth.currentUser!!
                            //se sube a la db
                            val intent = Intent(this@DatosActivity, Datos2Activity::class.java)
                            intent.putExtra("correo", correo)
                            intent.putExtra("uid", user.uid)
                            startActivity(intent)
                        }
                        else{
                            // error
                            if(task.exception is FirebaseAuthUserCollisionException){
                                progressBar.visibility = View.GONE
                                Toast.makeText(applicationContext, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                            }

                            else{
                                progressBar.visibility = View.GONE
                                Toast.makeText(applicationContext, "Hubo un problema con el servidor, intenta mas tarde", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
                }
                //si alguno esta vacio manda mensaje de que se llenen todos los campos
                else if (!notEmpty){
                    Toast.makeText(applicationContext, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show()

                    //pone color rolo
                    requestFocusAll(correo, contrasenia, confirmarContrasenia)
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
    fun checkAll(correo: String, contrasenia: String, confirmarContrasenia: String) : Boolean{
        if(correo.trim().length>0 && contrasenia.trim().length>0 && confirmarContrasenia.trim().length>0)
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
        if(contrasenia.length>=6)
            return true
        return false
    }

    //si algo esta vacio lo pone en rojo
    fun requestFocusAll(correo: String, contrasenia: String, confirmarContrasenia: String){
        if(correo.trim().length==0){
            correoText.requestFocus()
        }
        if(contrasenia.trim().length==0){
            contraseñaText.requestFocus()
        }
        if(confirmarContrasenia.trim().length==0){
            confirmarContraseñaText.requestFocus()
        }
    }

    fun checkEmail(email: String): Boolean {
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return true
        return false
    }
}


//FALTA: darle formato DATE a la regla
