package com.example.mnlgu.prototipo1appembarazo.StartActivities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.mnlgu.prototipo1appembarazo.R
import com.example.mnlgu.prototipo1appembarazo.Tabs.MainTabsActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        //base de autenticacion
        val mAuth = FirebaseAuth.getInstance()

        var email : String
        var password : String

        continueButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                email = emailText.text.toString()
                password = passwordText.text.toString()

                val checkEmail: Boolean = checkEmail(email)
                val checkAll: Boolean = checkAll(email, password)

                progressBar.visibility = View.VISIBLE

                if(checkEmail && checkAll){
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                            task: Task<AuthResult> ->
                        if(task.isSuccessful){
                            progressBar.visibility = View.GONE
                            val intent = Intent(this@LoginActivity, MainTabsActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            progressBar.visibility = View.GONE
                            Toast.makeText(applicationContext, task.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else if(!checkAll){
                    checkFocus(email, password)
                    progressBar.visibility = View.GONE
                    Toast.makeText(applicationContext, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show()
                }
                else if(!checkEmail){
                    emailText.requestFocus()
                    progressBar.visibility = View.GONE
                    Toast.makeText(applicationContext, "Ingresa un email valido", Toast.LENGTH_SHORT).show()
                }
            }
        })

        //cambiar de ventana
        //val intent = Intent(this@LoginActivity, MainTabsActivity::class.java)
        //startActivity(intent)


        newUser.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent(this@LoginActivity, DatosActivity::class.java)
                startActivity(intent)
            }
        })
    }

    fun checkEmail(email: String): Boolean {
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return true
        return false
    }

    fun checkAll(email: String, password: String): Boolean{
        if(email.trim().length > 0 && password.trim().length > 0)
            return true
        return false
    }

    fun checkFocus(email: String, password: String){
        if(email.trim().length == 0)
            emailText.requestFocus()
        if(password.trim().length == 0)
            emailText.requestFocus()
    }
}
