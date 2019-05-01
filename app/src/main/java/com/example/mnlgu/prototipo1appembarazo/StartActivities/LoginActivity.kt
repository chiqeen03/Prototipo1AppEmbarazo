package com.example.mnlgu.prototipo1appembarazo.StartActivities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mnlgu.prototipo1appembarazo.R
import com.example.mnlgu.prototipo1appembarazo.Tabs.MainTabsActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()


        val db : FirebaseFirestore = FirebaseFirestore.getInstance() //database
        /*
        * db.collection("users")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
        * */


        continueButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                //cambiar aca para que acepte lo de la base de datos
                //------------------------------------------------------------------------------------------------------
                if(emailText.text.toString().equals("admin") && passwordText.text.toString().equals("password")){
                    val intent = Intent(this@LoginActivity, MainTabsActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this@LoginActivity, "Incorrect user/password", Toast.LENGTH_SHORT).show()
                }
                //------------------------------------------------------------------------------------------------------
            }
        })

        newUser.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent(this@LoginActivity, DatosActivity::class.java)
                startActivity(intent)
            }
        })
    }
}
