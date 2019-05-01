package com.example.mnlgu.prototipo1appembarazo.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mnlgu.prototipo1appembarazo.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_main.*

class FragmentMain : Fragment() {

    lateinit var nameTextView: TextView
    lateinit var mAuth: FirebaseAuth
    var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        user = mAuth.currentUser

        // NO FUNCIONA
        /*
        var userFirebaseAuth = mAuth.currentUser

        if(userFirebaseAuth != null){
            if(userFirebaseAuth.displayName != null){
                nameText.setText(userFirebaseAuth.displayName.toString())
            }
        }
        */
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    fun init(){
        nameTextView = view!!.findViewById(R.id.nameText)
        nameTextView.text = user!!.displayName
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
