package com.example.mnlgu.prototipo1appembarazo.Fragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.mnlgu.prototipo1appembarazo.Callbacks.MutableMapCallback
import com.example.mnlgu.prototipo1appembarazo.DB.AuxiliarDatabase
import com.example.mnlgu.prototipo1appembarazo.DietaActivities.DietaEvitar
import com.example.mnlgu.prototipo1appembarazo.DietaActivities.DietaGrasas
import com.example.mnlgu.prototipo1appembarazo.DietaActivities.DietaHidratosDeCarbono
import com.example.mnlgu.prototipo1appembarazo.DietaActivities.DietaProteina
import com.example.mnlgu.prototipo1appembarazo.FireBaseData.FireBaseHelper
import com.example.mnlgu.prototipo1appembarazo.MainActivities.MainDrawerActivity
import com.example.mnlgu.prototipo1appembarazo.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FragmentDieta : Fragment(){

    lateinit var hidratosDeCarbonoButton: Button
    lateinit var proteinasButton: Button
    lateinit var grasasButton: Button
    lateinit var evitarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        init()
    }

    //aca se toman los text fields
    fun init(){
        hidratosDeCarbonoButton = view!!.findViewById(R.id.carbohidratosButton)
        hidratosDeCarbonoButton.setOnClickListener {
            val intent = Intent(context!!, DietaHidratosDeCarbono::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        proteinasButton = view!!.findViewById(R.id.proteinasButton)
        proteinasButton.setOnClickListener {
            val intent = Intent(context!!, DietaProteina::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        grasasButton = view!!.findViewById(R.id.grasasButton)
        grasasButton.setOnClickListener {
            val intent = Intent(context!!, DietaGrasas::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        evitarButton = view!!.findViewById(R.id.evitarButton)
        evitarButton.setOnClickListener {
            val intent = Intent(context!!, DietaEvitar::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dieta, container, false)
    }

}
