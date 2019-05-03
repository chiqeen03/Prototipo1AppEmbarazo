package com.example.mnlgu.prototipo1appembarazo.Fragments

import android.content.Context
import android.net.Uri
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

class FragmentEjercicios : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        init()
    }

    //aca se toman los text fields
    fun init(){

    }

    //aqui se setea la info en los text fields
    fun setData(){

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ejercicio, container, false)
    }
}
