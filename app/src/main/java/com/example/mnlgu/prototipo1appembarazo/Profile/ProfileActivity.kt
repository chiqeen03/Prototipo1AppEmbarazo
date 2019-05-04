package com.example.mnlgu.prototipo1appembarazo.Profile

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
import java.util.HashMap

class ProfileActivity : AppCompatActivity(), MutableMapCallback {
    override fun returnMutableMap(map: MutableMap<String, Any>) {
        datosUsuario = map
        setData()
    }

    lateinit var mAuth: FirebaseAuth
    var user: FirebaseUser? = null
    lateinit var uid : String
    var helper : FireBaseHelper = FireBaseHelper.instance
    private lateinit var datosUsuario: MutableMap<String, Any>
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        user = mAuth.currentUser

        uid = user!!.uid

        db = FirebaseFirestore.getInstance()

        var userMap : MutableMap<String, Any> = HashMap()


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
    }
}
