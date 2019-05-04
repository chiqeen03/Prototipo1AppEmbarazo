package com.example.mnlgu.prototipo1appembarazo.Profile

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.mnlgu.prototipo1appembarazo.Callbacks.MutableMapCallback
import com.example.mnlgu.prototipo1appembarazo.FireBaseData.FireBaseHelper
import com.example.mnlgu.prototipo1appembarazo.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_profile.*
import java.lang.Double.parseDouble
import java.util.*

class ProfileActivity : AppCompatActivity(), MutableMapCallback {
    override fun returnMutableMap(map: MutableMap<String, Any>) {
        datosUsuario = map
        setData()
        progressBar.visibility = View.GONE
    }

    lateinit var mAuth: FirebaseAuth
    var user: FirebaseUser? = null
    lateinit var uid : String
    var helper : FireBaseHelper = FireBaseHelper.instance
    private lateinit var datosUsuario: MutableMap<String, Any>
    lateinit var db: FirebaseFirestore

    lateinit var mDateSetListener: DatePickerDialog.OnDateSetListener
    var reglaDia: Int = 0
    var reglaMes: Int = 0
    var reglaAnio: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.hide()

        progressBar.visibility = View.VISIBLE

        mAuth = FirebaseAuth.getInstance()
        user = mAuth.currentUser

        uid = user!!.uid

        db = FirebaseFirestore.getInstance()

        var userMap : MutableMap<String, Any> = HashMap()


        //Auxiliares del calendario

        reglaDia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        reglaMes = Calendar.getInstance().get(Calendar.MONTH)
        reglaAnio = Calendar.getInstance().get(Calendar.YEAR)
        //https://www.youtube.com/watch?v=hwe1abDO2Ag
        //--------------------------------------------------------------------------------------------------------------
        //dateAux = setDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH))

        calendarButton.setOnClickListener {
            val year: Int = datosUsuario.get("reglaAnio").toString().toInt()
            val month: Int = datosUsuario.get("reglaMes").toString().toInt()
            val day: Int = datosUsuario.get("reglaDia").toString().toInt()

            val dialog = DatePickerDialog(this, android.R.style.Theme_DeviceDefault_Dialog, mDateSetListener, year, month, day)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

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

        if(user != null){
            helper.getDataFromFirestore("users", user?.uid.toString(), this )
        }

        cancelar.setOnClickListener {
            finish()
        }

        guardar.setOnClickListener {

            val nameString = nameText.text.toString()
            val pesoString = pesoText.text.toString()
            val estaturaString = estaturaText.text.toString()

            val pesoIsNumber: Boolean = checkIfNumber(pesoString)
            val estaturaIsNumber: Boolean = checkIfNumber(estaturaString)

            if(pesoIsNumber && estaturaIsNumber){
                if(user != null){

                    var profile : UserProfileChangeRequest = UserProfileChangeRequest.Builder()
                        .setDisplayName(nameText.text.toString())
                        .build()

                    //updatea el perfil
                    user?.updateProfile(profile)?.addOnCompleteListener{task ->
                        if(task.isSuccessful){
                            //Toast.makeText(applicationContext, firebaseUser.displayName.toString(), Toast.LENGTH_SHORT).show()
                        } else{
                            //Toast.makeText(applicationContext, task.exception?.message, Toast.LENGTH_SHORT).show()
                        }

                    }

                    userMap.put("nombre", nameString)
                    userMap.put("peso", pesoString)
                    userMap.put("estatura", estaturaString)
                    userMap.put("reglaDia", reglaDia)
                    userMap.put("reglaMes", reglaMes)
                    userMap.put("reglaAnio", reglaAnio)
                    progressBar.visibility = View.VISIBLE

                    db.collection("users")
                        .document(uid)
                        .update(userMap)
                        .addOnSuccessListener{
                            progressBar.visibility = View.GONE
                            Toast.makeText(applicationContext, "Datos actualizados", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        .addOnFailureListener{
                            progressBar.visibility = View.GONE
                            Toast.makeText(applicationContext, "Ocurrio un error con el servidor", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            else {
                Toast.makeText(applicationContext, "Por favor ingresa formatos validos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkIfNumber(number: String) : Boolean{
        return number.matches("-?\\d+(\\.\\d+)?".toRegex())
    }

    private fun setData() {
        nameText.setText(datosUsuario.get("nombre").toString())
        pesoText.setText(datosUsuario.get("peso").toString())
        estaturaText.setText(datosUsuario.get("estatura").toString())

        //datos para setear el calendario cuando no se modifica
        reglaDia = datosUsuario.get("reglaDia").toString().toInt()
        reglaMes = datosUsuario.get("reglaMes").toString().toInt()
        reglaAnio = datosUsuario.get("reglaAnio").toString().toInt()

        val auxString = "" + reglaDia + "/" + monthToString(reglaMes+1) + "/" + reglaAnio
        ultimaRegla.text = auxString
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
