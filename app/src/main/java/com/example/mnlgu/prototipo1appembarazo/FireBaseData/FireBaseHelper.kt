package com.example.mnlgu.prototipo1appembarazo.FireBaseData

import com.example.mnlgu.prototipo1appembarazo.Callbacks.MutableMapCallback
import com.google.firebase.firestore.FirebaseFirestore

class FireBaseHelper private constructor(){
    //Access a Cloud Firestore instance
    var db = FirebaseFirestore.getInstance()

    object Holder{
        val value = synchronized(FireBaseHelper::class.java){
            FireBaseHelper()
        }
    }

    companion object {
        val instance: FireBaseHelper by lazy {Holder.value}
    }

    fun getDataFromFirestore(collectionName: String, usrID: String, mapCallback: MutableMapCallback){

        var auxMap: MutableMap<String, Any>

        db.collection(collectionName)
            .get()
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    for(document in task.result){
                        if(document.id == usrID){
                            auxMap = document.data
                            mapCallback.returnMutableMap(auxMap)
                        }
                    }
                } else {

                }
            }
    }
}
