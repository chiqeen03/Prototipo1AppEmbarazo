package com.example.mnlgu.prototipo1appembarazo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mnlgu.prototipo1appembarazo.StartActivities.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_datos2.*

class Datos2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos2)

        //database
        val db : FirebaseFirestore = FirebaseFirestore.getInstance() //database

        val mAuth = FirebaseAuth.getInstance()

        //HashMap Auxiliar
        var user : MutableMap<String, Any> = HashMap()

        //escondemos la action bar
        supportActionBar?.hide()

        //variables para hasMap
        var uid: String
        var nombre: String
        var correo: String
        var peso: String
        var estatura: String
        var regla: String
        var semanaGestacion: String

        var firebaseUser: FirebaseUser? = mAuth.currentUser

        crearUsuarioButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                uid = intent.getStringExtra("uid")
                nombre = nameText.text.toString()
                correo = intent.getStringExtra("correo")
                peso = pesoText.text.toString()
                estatura = estaturaText.text.toString()
                regla = reglaText.text.toString()
                semanaGestacion = semanaText.text.toString()

                //booleanos auxiliares
                var notEmpty : Boolean = checkAll(nombre, peso, estatura, regla, semanaGestacion)

                if (notEmpty){

                    if(firebaseUser != null){
                        //para updatear el perfil
                        var profile : UserProfileChangeRequest = UserProfileChangeRequest.Builder()
                            .setDisplayName(nombre)
                            .build()

                        //updatea el perfil
                        firebaseUser.updateProfile(profile).addOnCompleteListener{task ->
                            if(task.isSuccessful){
                                //Toast.makeText(applicationContext, firebaseUser.displayName.toString(), Toast.LENGTH_SHORT).show()
                            }
                            else{
                                Toast.makeText(applicationContext, task.exception?.message, Toast.LENGTH_SHORT).show()
                            }

                        }
                    }

                    user.put("_id", uid)
                    user.put("nombre", nombre)
                    user.put("correo", correo)
                    user.put("peso", peso.toFloat())
                    user.put("estatura", estatura.toFloat())
                    user.put("regla", regla)
                    user.put("semanaGestacion", semanaGestacion.toInt())

                    progressBar.visibility = View.VISIBLE

                    //se sube a la db
                    db.collection("users")
                        .add(user)
                        // si es exitoso
                        .addOnSuccessListener {
                            progressBar.visibility = View.GONE
                            Toast.makeText(applicationContext, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show()
                            //inicia la siguiente actividad
                            val intent = Intent(this@Datos2Activity, LoginActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                        }
                        //si no es exitoso
                        .addOnFailureListener {
                            progressBar.visibility = View.GONE
                            Toast.makeText(applicationContext, "Hay un problema con el servidor. Intenta mas tarde", Toast.LENGTH_SHORT).show()
                        }
                }
                //si alguno esta vacio manda mensaje de que se llenen todos los campos
                else if (!notEmpty){
                    Toast.makeText(applicationContext, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show()

                    //pone color rolo
                    requestFocusAll(nombre, peso, estatura, regla, semanaGestacion)
                }
            }
        })
    }

    fun checkAll(nombre: String, peso: String, estatura: String, regla: String, semanaGestacion: String) : Boolean{
        if(nombre.trim().length>0 && peso.trim().length>0 && estatura.trim().length>0 && regla.trim().length>0 && semanaGestacion.trim().length>0)
            return true
        return false
    }

    fun requestFocusAll(nombre: String, peso: String, estatura: String, regla: String, semanaGestacion: String){
        if(nombre.trim().length==0){
            nameText.requestFocus()
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
}
