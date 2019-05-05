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

class FragmentMicronutrimentos : Fragment() {

    lateinit var hierroButton: Button
    lateinit var acidoFolicoButton: Button
    lateinit var vitaminaAButton: Button
    lateinit var calcioYVitaminaDButton: Button
    lateinit var zincButton: Button

    lateinit var myDialog: Dialog

    val auxDB: AuxiliarDatabase = AuxiliarDatabase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myDialog = Dialog(context!!)
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    //aca se toman los text fields
    fun init(){
        hierroButton = view!!.findViewById(R.id.hierroButton)
        hierroButton.setOnClickListener {
            showInfo("Hierro", auxDB.micronutrimentosDB.get("hierro").toString())
        }

        acidoFolicoButton = view!!.findViewById(R.id.acidoFolicoButton)
        acidoFolicoButton.setOnClickListener {
            showInfo("Ácido Fólico", auxDB.micronutrimentosDB.get("acido folico").toString())
        }

        vitaminaAButton = view!!.findViewById(R.id.vitaminaAButton)
        vitaminaAButton.setOnClickListener {
            showInfo("Vitamina A", auxDB.micronutrimentosDB.get("vitamina a").toString())
        }

        calcioYVitaminaDButton = view!!.findViewById(R.id.calcioYVitaminaDButton)
        calcioYVitaminaDButton.setOnClickListener {
            showInfo("Calcio y Vitamina D", auxDB.micronutrimentosDB.get("calcio y vitamina d").toString())
        }

        zincButton = view!!.findViewById(R.id.zincButton)
        zincButton.setOnClickListener {
            showInfo("Zinc", auxDB.micronutrimentosDB.get("zinc").toString())
        }

    }

    private fun showInfo(titulo: String, info: String){
        myDialog.setContentView(R.layout.popup_micronutrimentos)

        val closeButton: Button = myDialog.findViewById(R.id.microNutriClose)
        closeButton.setOnClickListener {
            myDialog.dismiss()
        }

        val tituloInfo: TextView = myDialog.findViewById(R.id.microNutriInfo)
        tituloInfo.text = titulo

        val infoText: TextView = myDialog.findViewById(R.id.microNutriText)
        infoText.text = info

        myDialog.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_micronutrimentos, container, false)
    }
}
