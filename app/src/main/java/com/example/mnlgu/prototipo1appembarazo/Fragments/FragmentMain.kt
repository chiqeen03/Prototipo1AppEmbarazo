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
import com.google.firebase.firestore.CollectionReference
import kotlinx.android.synthetic.main.fragment_main.*

class FragmentMain : Fragment(), MutableMapCallback {

    override fun returnMutableMap(map: MutableMap<String, Any>) {
        datosUsuario = map
        setData()
    }

    lateinit var nameTextView: TextView
    lateinit var mAuth: FirebaseAuth
    var user: FirebaseUser? = null
    lateinit var uid : String

    var helper : FireBaseHelper = FireBaseHelper.instance

    private lateinit var datosUsuario: MutableMap<String, Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        user = mAuth.currentUser

        uid = user!!.uid


        if(user != null){
            helper.getDataFromFirestore("users", user?.uid.toString(), this )
        }

    }

    override fun onStart() {
        super.onStart()
        init()
    }

    //aca se toman los text fields
    fun init(){
        nameTextView = view!!.findViewById(R.id.nameText)
        helper.getDataFromFirestore("users", user?.uid.toString(), this )
    }

    //aqui se setea la info en los text fields
    fun setData(){
        nameTextView.text = datosUsuario.get("nombre").toString()

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
