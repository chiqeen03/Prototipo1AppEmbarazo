package com.example.mnlgu.prototipo1appembarazo.Fragments

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.mnlgu.prototipo1appembarazo.DB.AuxiliarDatabase
import com.example.mnlgu.prototipo1appembarazo.R

class FragmentEjercicios : Fragment(){

    val auxDB = AuxiliarDatabase()

    lateinit var ejerciciosDB: Map<String, String>

    lateinit var myDialog: Dialog

    lateinit var ejerciciosText: TextView
    lateinit var aerobicosButton: Button
    lateinit var fuerzaButton: Button
    lateinit var consideracionesButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ejerciciosDB = auxDB.ejerciciosDB

        myDialog = Dialog(context!!)
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    //aca se toman los text fields
    fun init(){
        ejerciciosText = view!!.findViewById(R.id.infoEjercicios)
        ejerciciosText.text = ejerciciosDB.get("descripcion")

        aerobicosButton = view!!.findViewById(R.id.aerobicoButton)
        aerobicosButton.setOnClickListener {
            showPopUp("Ejercicio Aeróbico", ejerciciosDB.get("aerobicos").toString())
        }

        fuerzaButton = view!!.findViewById(R.id.fuerzaButton)
        fuerzaButton.setOnClickListener {
            showPopUp("Ejercicio de Fuerza", ejerciciosDB.get("fuerza").toString())
        }

        consideracionesButton = view!!.findViewById(R.id.consideracionesButton)
        consideracionesButton.setOnClickListener {
            showPopUp("Consideraciones especiales", ejerciciosDB.get("consideraciones").toString())
        }
    }

    private fun showPopUp(tituloEjercicio: String, infoEjercicio: String){
        //esto se tiene que cambiar para cada vista
        myDialog.setContentView(R.layout.popup_ejercicios)

        val closeButton: Button = myDialog.findViewById(R.id.closeButton)
        val tituloTextView: TextView = myDialog.findViewById(R.id.tituloEjercicioText)
        val infoTextView: TextView = myDialog.findViewById(R.id.informacionEjercicioText)

        closeButton.setOnClickListener {
            myDialog.dismiss()
        }

        tituloTextView.text = tituloEjercicio

        infoTextView.text = infoEjercicio

        myDialog.show()
    }

    /*
    Pop up generico

    private fun showPopUp(){
        //esto se tiene que cambiar para cada vista
        myDialog.setContentView(R.layout.vista_bebe)

        val closeButton: Button = myDialog.findViewById(R.id.closeButton)
        closeButton.setOnClickListener {
            myDialog.dismiss()
        }

        myDialog.show()
    }
    */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ejercicio, container, false)
    }
}
