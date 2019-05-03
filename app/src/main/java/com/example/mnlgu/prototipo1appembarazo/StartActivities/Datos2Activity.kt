package com.example.mnlgu.prototipo1appembarazo.StartActivities

import android.app.DatePickerDialog
import android.content.Intent
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
    lateinit var dateDisplay : TextView
    lateinit var dateAux: Date

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

        //CHECAR NO SIRVE
        //Auxiliares del calendario
        //https://www.youtube.com/watch?v=hwe1abDO2Ag
        //--------------------------------------------------------------------------------------------------------------
        dateAux = setDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
        dateDisplay = findViewById(ultimaRegla.id)

        dateDisplay.setOnClickListener {
            var cal: Calendar = Calendar.getInstance()
            var year: Int = cal.get(Calendar.YEAR)
            var month: Int = cal.get(Calendar.MONTH)
            var day: Int = cal.get(Calendar.DAY_OF_MONTH)

            var dialog = DatePickerDialog(applicationContext, android.R.style.Theme, mDateSetListener, year, month, day)
            dialog.show()
        }

        mDateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            var dayAux = day
            var monthAux = month
            var yearAux = year

            var auxString = "" + dayAux + "/" + (monthAux+1) + "/" + yearAux

            ultimaRegla.text = auxString
            dateAux = setDate(yearAux, monthAux, dayAux)
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
                user.put("regla", dateAux)

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

    fun setDate(year: Int, month:Int, day: Int): Date{

        var calendar: Calendar = Calendar.getInstance()
        calendar.set(year, month, day)

        return calendar.time
    }
}
