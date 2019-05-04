package com.example.mnlgu.prototipo1appembarazo.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mnlgu.prototipo1appembarazo.Callbacks.MutableMapCallback
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        user = mAuth.currentUser

        uid = user!!.uid

        //agarra los datos de la base de datos
        if(user != null){
            helper.getDataFromFirestore("users", uid, this )
        }
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

        val calorias = calcularCalorias(peso, estatura, trimestre, edad)
        caloriasText.text = calorias.toInt().toString()
        //caloriasText.text = IMC.toString()


        //TODO: aplicar las formulas para mostrar la informacion de peso objetivo semanal y trimestral
    }

    private fun calcularCalorias(peso: Double, estatura: Double, trimestre: Int, edad: Int): Double {
        val IMC = calcularIMC(peso, estatura)
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

    //NO MOVER
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}
