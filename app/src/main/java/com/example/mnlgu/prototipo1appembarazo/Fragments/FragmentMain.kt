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
        val peso = datosUsuario.get("peso").toString().toFloat()
        val estatura = datosUsuario.get("estatura").toString().toFloat()
        val IMC = calcularIMC(peso, estatura)
        val edad = datosUsuario.get("edad")
        val semanaGestacion = diffBtwnRuleAndToday()

        //setea los text fields
        nameTextView.text = datosUsuario.get("nombre").toString()
        semanaTextView.text = semanaGestacion.toString()
    }

    private fun calcularIMC(peso: Float, estatura: Float): Float {
        return (peso / (estatura * estatura))
    }


    private fun diffBtwnRuleAndToday() : Long{
        var diaAux = datosUsuario.get("reglaDia").toString()
        var mesAux = datosUsuario.get("reglaMes").toString()
        var anioAux = datosUsuario.get("reglaAnio").toString()

        if(diaAux.trim().length==1){
            diaAux = "0" + diaAux
        }
        if(mesAux.trim().length==1){
            mesAux = "0" + mesAux
        }

        //"2018-04-16T17:00:08.746Z"
        val ultimaRegla = LocalDateTime.parse("" + anioAux + "-" + mesAux + "-" + diaAux + "T00:00:00")
        val hoy = LocalDateTime.now()

        return (Duration.between(hoy, ultimaRegla).toDays().absoluteValue / 7)

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
