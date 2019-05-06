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

class FragmentDieta : Fragment(){

    lateinit var hidratosDeCarbonoButton: Button
    lateinit var proteinasButton: Button
    lateinit var grasasButton: Button
    lateinit var evitarButton: Button

    val auxDB: AuxiliarDatabase = AuxiliarDatabase()

    lateinit var myDialog: Dialog

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
        hidratosDeCarbonoButton = view!!.findViewById(R.id.carbohidratosButton)
        hidratosDeCarbonoButton.setOnClickListener {
            showPopUpHidratosDeCarbono()

        }

        proteinasButton = view!!.findViewById(R.id.proteinasButton)
        proteinasButton.setOnClickListener {
            showPopUpProteinas()
        }

        grasasButton = view!!.findViewById(R.id.grasasButton)
        grasasButton.setOnClickListener {
            showPopUpGrasas()
        }

        evitarButton = view!!.findViewById(R.id.evitarButton)
        evitarButton.setOnClickListener {
            showPopUpAEvitar()
        }
    }

    private fun showPopUpHidratosDeCarbono(){
        //esto se tiene que cambiar para cada vista
        myDialog.setContentView(R.layout.popup_dieta_hidratos_carbono)

        val closeButton: Button = myDialog.findViewById(R.id.closeButtonCarboHidratos)
        closeButton.setOnClickListener {
            myDialog.dismiss()
        }

        val fibraText: TextView = myDialog.findViewById(R.id.fibraText)
        fibraText.setOnClickListener {
            showInfo("Fibra", auxDB.dietaHidratosDeCarbono.get("fibra").toString(), 0)
        }

        val frutasYVerdurasText: TextView = myDialog.findViewById(R.id.frutasYVerdurasText)
        frutasYVerdurasText.setOnClickListener {
            showInfo("Frutas y Verduras", auxDB.dietaHidratosDeCarbono.get("frutas y verduras").toString(), 0)
        }

        val leguminosasText: TextView = myDialog.findViewById(R.id.leguminosasText)
        leguminosasText.setOnClickListener {
            showInfo("Leguminosas", auxDB.dietaHidratosDeCarbono.get("leguminosas").toString(), 0)
        }

        val azucaresText: TextView = myDialog.findViewById(R.id.azucaresText)
        azucaresText.setOnClickListener {
            showInfo("Azúcares", auxDB.dietaHidratosDeCarbono.get("azucares").toString(), 0)
        }

        myDialog.show()
    }

    private fun showPopUpProteinas(){
        //esto se tiene que cambiar para cada vista
        myDialog.setContentView(R.layout.popup_dieta_proteinas)

        val closeButton: Button = myDialog.findViewById(R.id.closeButtonProteinas)
        closeButton.setOnClickListener {
            myDialog.dismiss()
        }

        val carneRojaText: TextView = myDialog.findViewById(R.id.carneRojaText)
        carneRojaText.setOnClickListener {
            showInfo("Carnes Rojas", auxDB.dietaProteinas.get("carnes rojas").toString(), 1)
        }

        val pescadoText: TextView = myDialog.findViewById(R.id.pescadoText)
        pescadoText.setOnClickListener {
            showInfo("Pescado", auxDB.dietaProteinas.get("pescado").toString(), 1)
        }

        val lacteosText: TextView = myDialog.findViewById(R.id.lacteosText)
        lacteosText.setOnClickListener {
            showInfo("Lacteos", auxDB.dietaProteinas.get("lacteos").toString(), 1)
        }

        val embutidosText: TextView = myDialog.findViewById(R.id.embutidosText)
        embutidosText.setOnClickListener {
            showInfo("Embutidos", auxDB.dietaProteinas.get("embutidos").toString(), 1)
        }

        val otrosText: TextView = myDialog.findViewById(R.id.otrosText)
        otrosText.setOnClickListener {
            showInfo("Otros", auxDB.dietaProteinas.get("otros").toString(), 1)
        }

        myDialog.show()
    }

    private fun showPopUpGrasas(){
        //esto se tiene que cambiar para cada vista
        myDialog.setContentView(R.layout.popup_dieta_grasas)

        val closeButton: Button = myDialog.findViewById(R.id.closeButtonGrasas)
        closeButton.setOnClickListener {
            myDialog.dismiss()
        }

        val omega3Text: TextView = myDialog.findViewById(R.id.omega3Text)
        omega3Text.setOnClickListener {
            showInfo("Omega 3 (ácido a- linolénico)", auxDB.dietaGrasas.get("omega 3").toString(), 2)
        }

        val omega6Text: TextView = myDialog.findViewById(R.id.omega6Text)
        omega6Text.setOnClickListener {
            showInfo("Omega 6 (ácido linoleico)", auxDB.dietaGrasas.get("omega 6").toString(), 2)
        }

        val saturadaText: TextView = myDialog.findViewById(R.id.saturadaText)
        saturadaText.setOnClickListener {
            showInfo("Saturadas", auxDB.dietaGrasas.get("saturadas").toString(), 2)
        }

        val transText: TextView = myDialog.findViewById(R.id.transText)
        transText.setOnClickListener {
            showInfo("Trans", auxDB.dietaGrasas.get("trans").toString(), 2)
        }

        myDialog.show()
    }

    private fun showPopUpAEvitar(){
        //esto se tiene que cambiar para cada vista
        myDialog.setContentView(R.layout.popup_dieta_a_evitar)

        val closeButton: Button = myDialog.findViewById(R.id.closeButtonAEvitar)
        closeButton.setOnClickListener {
            myDialog.dismiss()
        }

        val alcoholText: TextView = myDialog.findViewById(R.id.alcoholText)
        alcoholText.setOnClickListener {
            showInfo("Alcohol", auxDB.dietaAEvitar.get("alcohol").toString(), 3)
        }

        val cafeText: TextView = myDialog.findViewById(R.id.cafeText)
        cafeText.setOnClickListener {
            showInfo("Café", auxDB.dietaAEvitar.get("cafe").toString(), 3)
        }

        myDialog.show()
    }

    private fun showInfo(titulo: String, info: String, i: Int){
        myDialog.setContentView(R.layout.popup_dieta_info)

        val closeButton: Button = myDialog.findViewById(R.id.closeInfo)
        closeButton.setOnClickListener {
            myDialog.dismiss()
        }

        val tituloInfo: TextView = myDialog.findViewById(R.id.tituloInfo)
        tituloInfo.text = titulo

        val infoText: TextView = myDialog.findViewById(R.id.infoText)
        infoText.text = info

        val regresar: Button = myDialog.findViewById(R.id.backInfo)
        regresar.setOnClickListener {
            goBack(i)
        }

        myDialog.show()
    }

    fun goBack(i: Int){
        when (i){
            0 -> showPopUpHidratosDeCarbono()
            1 -> showPopUpProteinas()
            2 -> showPopUpGrasas()
            3 -> showPopUpAEvitar()
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
