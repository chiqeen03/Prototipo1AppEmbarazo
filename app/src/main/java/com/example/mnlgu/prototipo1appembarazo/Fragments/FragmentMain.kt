package com.example.mnlgu.prototipo1appembarazo.Fragments

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.mnlgu.prototipo1appembarazo.Callbacks.MutableMapCallback
import com.example.mnlgu.prototipo1appembarazo.DB.AuxiliarDatabase
import com.example.mnlgu.prototipo1appembarazo.FireBaseData.FireBaseHelper
import com.example.mnlgu.prototipo1appembarazo.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_main.*
import java.time.Duration
import java.time.LocalDateTime
import kotlin.math.absoluteValue

class FragmentMain : Fragment(), MutableMapCallback {

    override fun returnMutableMap(map: MutableMap<String, Any>) {
        datosUsuario = map
        setData()
    }

    //text Fields to edit
    lateinit var nameTextView: TextView
    lateinit var semanaTextView: TextView

    //database
    lateinit var mAuth: FirebaseAuth
    var user: FirebaseUser? = null
    lateinit var uid : String
    var helper : FireBaseHelper = FireBaseHelper.instance

    //datos del usuario sacados de la db
    private lateinit var datosUsuario: MutableMap<String, Any>

    //dialogo
    lateinit var myDialog: Dialog
    lateinit var bebeButton: Button
    lateinit var babyTextDB: MutableMap<Int, String>

    //base de datos auxiliar con todos los textos
    val auxDB = AuxiliarDatabase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        user = mAuth.currentUser

        uid = user!!.uid

        //agarra los datos de la base de datos
        if(user != null){
            helper.getDataFromFirestore("users", uid, this )
        }

        //si va en un fragment se usa context y si es en una actividad se usa this
        myDialog = Dialog(context!!)

        //se jala la db
        babyTextDB = auxDB.babyInfoDB

    }

    override fun onStart() {
        super.onStart()
        init()
    }

    //aca se toman los text fields
    fun init(){
        uid = user!!.uid

        //setea los textViews
        nameTextView = view!!.findViewById(R.id.nameText)
        semanaTextView = view!!.findViewById(R.id.semana)

        helper.getDataFromFirestore("users", uid, this )

        bebeButton = view!!.findViewById(R.id.verBebe)
    }

    //aqui se setea la info en los text fields
    fun setData(){

        //variables a utilizar
        val peso = datosUsuario.get("peso").toString().toDouble()
        val estatura = (datosUsuario.get("estatura").toString().toDouble())/100
        val IMC = calcularIMC(peso, estatura)
        val edad = datosUsuario.get("edad").toString().toInt()
        val semanaGestacion = calcularSemanas()
        val trimestre = calcularTrimestre(semanaGestacion)

        //setea los text fields
        nameTextView.text = datosUsuario.get("nombre").toString()
        semanaTextView.text = semanaGestacion.toString()

        val calorias = "" + calcularCalorias(IMC, peso, estatura, trimestre, edad).toInt().toString() + " kCal"
        caloriasText.text = calorias

        val pesoSemanal = "" + calcularPesoSemanal(IMC, peso, trimestre, semanaGestacion).toString() + " Kg"
        pesoSemanalText.text = pesoSemanal

        val pesoTrimestral = "" + calcularPesoTrimestral(IMC, peso, trimestre).toString() + " Kg"
        pesoTrimestralText.text = pesoTrimestral

        //para popUp

        val imgSrc: Int = getImageSource(semanaGestacion)
        val textSrc: String = getTextSource(semanaGestacion)

        bebeButton.setOnClickListener {
            showPopUp(imgSrc, textSrc)
        }

    }

    private fun getTextSource(semanaGestacion: Int): String {

        when(semanaGestacion){
            in 0..4 -> return babyTextDB.get(4)!!
            5 -> return babyTextDB.get(5)!!
            6 -> return babyTextDB.get(6)!!
            7 -> return babyTextDB.get(7)!!
            8 -> return babyTextDB.get(8)!!
            9 -> return babyTextDB.get(9)!!
            10 -> return babyTextDB.get(10)!!
            11,12 -> return babyTextDB.get(12)!!
            13, 14 -> return babyTextDB.get(14)!!
            15, 16 -> return babyTextDB.get(16)!!
            17, 18 -> return babyTextDB.get(18)!!
            19, 20 -> return babyTextDB.get(20)!!
            21, 22 -> return babyTextDB.get(22)!!
            23, 24 -> return babyTextDB.get(24)!!
            25, 26 -> return babyTextDB.get(26)!!
            27, 28 -> return babyTextDB.get(28)!!
            29, 30 -> return babyTextDB.get(30)!!
            31, 32 -> return babyTextDB.get(32)!!
            33, 34 -> return babyTextDB.get(34)!!
            35, 36 -> return babyTextDB.get(36)!!
            37, 38 -> return babyTextDB.get(38)!!
            else -> return babyTextDB.get(40)!!
        }
    }

    private fun getImageSource(semanaGestacion: Int): Int {
        when(semanaGestacion){
            in 0..4 -> return R.drawable.feto4
            5 -> return R.drawable.feto5
            6 -> return R.drawable.feto6
            7 -> return R.drawable.feto7
            8 -> return R.drawable.feto8
            9 -> return R.drawable.feto9
            10 -> return R.drawable.feto10
            11,12 -> return R.drawable.feto12
            13, 14 -> return R.drawable.feto14
            15, 16 -> return R.drawable.feto16
            17, 18 -> return R.drawable.feto18
            19, 20 -> return R.drawable.feto20
            21, 22 -> return R.drawable.feto22
            23, 24 -> return R.drawable.feto24
            25, 26 -> return R.drawable.feto26
            27, 28 -> return R.drawable.feto28
            29, 30 -> return R.drawable.feto30
            31, 32 -> return R.drawable.feto32
            33, 34 -> return R.drawable.feto34
            35, 36 -> return R.drawable.feto36
            37, 38 -> return R.drawable.feto38
            else -> return R.drawable.feto40
        }
    }

    private fun calcularPesoTrimestral(IMC: Double, peso: Double, trimestre: Int): Double {
        val gananciaTrimestralTotal: Double

        val gananciaPrimerTrimestre: Double
        val gananciaSegundoTrimestre: Double
        val gananciaTercerTrimestre: Double

        if(IMC in 0.0..18.49999999){
            gananciaPrimerTrimestre = 1.7
            gananciaSegundoTrimestre = 6.6
            gananciaTercerTrimestre = 5.56
        }
        //peso normal
        else if(IMC in 18.5..24.9){
            gananciaPrimerTrimestre = 1.5
            gananciaSegundoTrimestre = 5.8
            gananciaTercerTrimestre = 4.9
        }
        //sobrepeso y obesidad
        else{
            gananciaPrimerTrimestre = 0.870
            gananciaSegundoTrimestre = 3.3
            gananciaTercerTrimestre = 2.7
        }

        if(trimestre == 1){
            gananciaTrimestralTotal = peso + gananciaPrimerTrimestre
        }
        else if (trimestre == 2){
            gananciaTrimestralTotal = peso + gananciaPrimerTrimestre + gananciaSegundoTrimestre
        }
        else{
            gananciaTrimestralTotal = peso + gananciaPrimerTrimestre + gananciaSegundoTrimestre + gananciaTercerTrimestre
        }

        return gananciaTrimestralTotal
    }

    private fun calcularPesoSemanal(IMC: Double, peso: Double, trimestre: Int, semanaGestacion: Int): Double {
        var gananciaSemanalTotal: Double

        val gananciaSemanalPrimerT: Double
        val gananciaSemanalSegundoT: Double
        val gananciaSemanalTercerT: Double

        //peso bajo
        if(IMC in 0.0..18.49999999){
            gananciaSemanalPrimerT = 0.134
            gananciaSemanalSegundoT = 0.476
            gananciaSemanalTercerT = 0.428
        }
        //peso normal
        else if(IMC in 18.5..24.9){
            gananciaSemanalPrimerT = 0.119
            gananciaSemanalSegundoT = 0.420
            gananciaSemanalTercerT = 0.378
        }
        //sobrepeso y obesidad
        else{
            gananciaSemanalPrimerT = 0.000067
            gananciaSemanalSegundoT = 0.238
            gananciaSemanalTercerT = 0.214
        }

        if(trimestre == 1){
            gananciaSemanalTotal = semanaGestacion * gananciaSemanalPrimerT
        }
        else if (trimestre == 2){
            val gananciaPrimerAux = 13 * gananciaSemanalPrimerT
            val gananciaSegundoAux = (semanaGestacion-13) * gananciaSemanalSegundoT
            gananciaSemanalTotal = gananciaPrimerAux + gananciaSegundoAux
        }
        else{
            val gananciaPrimerAux = 13 * gananciaSemanalPrimerT
            val gananciaSegundoAux = 14 * gananciaSemanalSegundoT
            val gananciaTercerAux = (semanaGestacion - 27) * gananciaSemanalTercerT

            gananciaSemanalTotal = gananciaPrimerAux + gananciaSegundoAux + gananciaTercerAux
        }

        return peso + gananciaSemanalTotal
    }

    private fun calcularCalorias(IMC: Double, peso: Double, estatura: Double, trimestre: Int, edad: Int): Double {
        var pesoAux = peso
        val numCal : Double
        val kcalEmbarazo: Int

        if(IMC !in 18.5..24.9){
            pesoAux = 22.5 * estatura * estatura
        }

        when(trimestre){
            1 -> kcalEmbarazo = 85
            2 -> kcalEmbarazo = 285
            else -> kcalEmbarazo = 475
        }

        //[(14. 818)(kg de peso pregestacional) + 486.6] [1.5]+ kcal embarazo
        if(edad in 18..30){
            numCal = ((14.818*pesoAux) + 486.6)*(1.5) + kcalEmbarazo
        }
        //[(8.126) (kg de peso pregestacional) + 845.6] [1.5]+ kcal embarazo
        else if (edad in 31..60){
            numCal = ((8.126*pesoAux) + 845.6)*(1.5) + kcalEmbarazo
        }
        else{
            numCal = 1800.0
        }

        return numCal
    }

    private fun calcularTrimestre(semanaGestacion: Int): Int {
        when(semanaGestacion){
            in 0..13 -> return 1
            in 14..27 -> return 2
            in 28..40 -> return 3
            else -> return 0
        }
    }

    private fun calcularIMC(peso: Double, estatura: Double): Double {
        return (peso / (estatura * estatura))
    }

    private fun calcularSemanas() : Int{
        var diaAux = datosUsuario.get("reglaDia").toString()
        val mesAuxInt = datosUsuario.get("reglaMes").toString().toInt() + 1
        var mesAux = mesAuxInt.toString()
        val anioAux = datosUsuario.get("reglaAnio").toString()

        if(diaAux.trim().length==1){
            diaAux = "0" + diaAux
        }
        if(mesAux.trim().length==1){
            mesAux = "0" + mesAux
        }

        //"2018-04-16T17:00:08.746Z"
        val ultimaRegla = LocalDateTime.parse("" + anioAux + "-" + mesAux + "-" + diaAux + "T00:00:00")
        val hoy = LocalDateTime.now()

        return (Duration.between(hoy, ultimaRegla).toDays().absoluteValue / 7).toInt()

    }


    private fun showPopUp(imgSrc: Int, textSrc: String){
        //esto se tiene que cambiar para cada vista
        myDialog.setContentView(R.layout.popup_bebe)

        val closeButton: Button = myDialog.findViewById(R.id.closeButton)
        val fetusImage: ImageView = myDialog.findViewById(R.id.fetoImagen)
        val fetusText: TextView = myDialog.findViewById(R.id.fetoTexto)

        closeButton.setOnClickListener {
            myDialog.dismiss()
        }

        fetusImage.setImageResource(imgSrc)

        fetusText.setText(textSrc)

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


    //NO MOVER
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}
