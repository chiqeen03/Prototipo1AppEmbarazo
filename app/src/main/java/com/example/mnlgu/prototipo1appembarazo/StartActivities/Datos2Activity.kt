package com.example.mnlgu.prototipo1appembarazo.StartActivities

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import com.example.mnlgu.prototipo1appembarazo.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_datos2.*
import java.util.*


class Datos2Activity : AppCompatActivity() {

    lateinit var mDateSetListener: DatePickerDialog.OnDateSetListener
    //lateinit var dateAux: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos2)

        //database
        val db : FirebaseFirestore = FirebaseFirestore.getInstance() //database
        val mAuth = FirebaseAuth.getInstance()
        var firebaseUser: FirebaseUser? = mAuth.currentUser

        //HashMap Auxiliar para crear usuario
        var user : MutableMap<String, Any> = HashMap()


        //escondemos la action bar
        supportActionBar?.hide()

        //variables para hashMap
        var uid: String
        var nombre: String
        var correo: String
        var peso: String
        var estatura: String
        var edad: String

        var reglaDia: Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        var reglaMes: Int = Calendar.getInstance().get(Calendar.MONTH)
        var reglaAnio: Int = Calendar.getInstance().get(Calendar.YEAR)
        //Auxiliares del calendario
        //https://www.youtube.com/watch?v=hwe1abDO2Ag
        //--------------------------------------------------------------------------------------------------------------
        //dateAux = setDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH))

        calendarButton.setOnClickListener {
            val cal: Calendar = Calendar.getInstance()
            val year: Int = cal.get(Calendar.YEAR)
            val month: Int = cal.get(Calendar.MONTH)
            val day: Int = cal.get(Calendar.DAY_OF_MONTH)

            val dialog = DatePickerDialog(this, android.R.style.Theme_DeviceDefault_Dialog, mDateSetListener, year, month, day)
            dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            dialog.show()
        }

        mDateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            reglaDia = day
            reglaMes = month
            reglaAnio = year

            val auxString = "" + reglaDia + "/" + monthToString(reglaMes+1) + "/" + reglaAnio

            ultimaRegla.text = auxString
            //dateAux = setDate(yearAux, monthAux, dayAux)
        }
        //--------------------------------------------------------------------------------------------------------------

        crearUsuarioButton.setOnClickListener {
            uid = intent.getStringExtra("uid")
            nombre = nameText.text.toString()
            correo = intent.getStringExtra("correo")
            peso = pesoText.text.toString()
            estatura = estaturaText.text.toString()
            edad = edadText.text.toString()

            //booleanos auxiliares
            var notEmpty : Boolean = checkAll(nombre, peso, estatura, edad)

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
                        } else{
                            Toast.makeText(applicationContext, task.exception?.message, Toast.LENGTH_SHORT).show()
                        }

                    }
                }

                user.put("_id", uid)
                user.put("nombre", nombre)
                user.put("correo", correo)
                user.put("peso", peso.toFloat())
                user.put("estatura", estatura.toFloat())
                user.put("edad", edad.toInt())
                user.put("reglaDia", reglaDia)
                user.put("reglaMes", reglaMes)
                user.put("reglaAnio", reglaAnio)

                progressBar.visibility = View.VISIBLE

                db.collection("users")
                    .document(uid)
                    .set(user)
                    .addOnSuccessListener {
                        progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show()
                        //inicia la siguiente actividad
                        val intent = Intent(this@Datos2Activity, LoginActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener {
                        progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext, "Hay un problema con el servidor. Intenta mas tarde", Toast.LENGTH_SHORT).show()
                    }
            }
            //si alguno esta vacio manda mensaje de que se llenen todos los campos
            else if (!notEmpty){
                Toast.makeText(applicationContext, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show()
                requestFocusAll(nombre, peso, estatura, edad)
            }
        }
    }

    fun checkAll(nombre: String, peso: String, estatura: String, edad: String) : Boolean{
        if(nombre.trim().isNotEmpty() && peso.trim().isNotEmpty() && estatura.trim().isNotEmpty() && edad.trim().isNotEmpty())
            return true
        return false
    }

    fun requestFocusAll(nombre: String, peso: String, estatura: String, edad: String){
        if(edad.trim().isEmpty()){
            edadText.requestFocus()
        }
        if(estatura.trim().isEmpty()){
            estaturaText.requestFocus()
        }
        if(peso.trim().isEmpty()){
            pesoText.requestFocus()
        }
        if(nombre.trim().isEmpty()){
            nameText.requestFocus()
        }
    }

    private fun monthToString(month: Int): String{
        when(month){
            1 -> return "Enero"
            2 -> return "Febrero"
            3 -> return "Marzo"
            4 -> return "Abril"
            5 -> return "Mayo"
            6 -> return "Junio"
            7 -> return "Julio"
            8 -> return "Agosto"
            9 -> return "Septiembre"
            10 -> return "Octubre"
            11 -> return "Noviembre"
            12 -> return "Diciembre"
            else -> return ""
        }
    }
}
