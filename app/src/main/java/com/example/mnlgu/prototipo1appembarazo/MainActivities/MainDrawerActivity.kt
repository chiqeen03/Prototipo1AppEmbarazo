package com.example.mnlgu.prototipo1appembarazo.MainActivities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.mnlgu.prototipo1appembarazo.Profile.ProfileActivity
import com.example.mnlgu.prototipo1appembarazo.R
import com.example.mnlgu.prototipo1appembarazo.StartActivities.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main_drawer.*
import kotlinx.android.synthetic.main.app_bar_main_drawer.*
import kotlinx.android.synthetic.main.content_main_drawer.*

class MainDrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var firebaseUser: FirebaseUser? = null
    var mAuth: FirebaseAuth? = null

    override fun onStart() {
        super.onStart()

        //se checa si esta loggeado el usuario
        if(firebaseUser == null){
            updateUI(firebaseUser)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_drawer)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //authentication
        mAuth = FirebaseAuth.getInstance()
        //usuario
        firebaseUser = mAuth?.currentUser

        //IMPORTANTE
        //Para encontrar las variables del drawer
        //--------------------------------------------------------------------------------------------------------------
        val whereIsView = findViewById<NavigationView>(R.id.nav_view)
        val headerLayout = whereIsView.getHeaderView(0)
        val userNameTextView = headerLayout.findViewById<TextView>(R.id.header_name)
        val userEmailTextView = headerLayout.findViewById<TextView>(R.id.header_email)
        val correoVerificadoTextView = headerLayout.findViewById<TextView>(R.id.header_correo_verificado)
        //--------------------------------------------------------------------------------------------------------------

        //no mover aca
        //--------------------------------------------------------------------------------------------------------------
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        //--------------------------------------------------------------------------------------------------------------


        //tabs and icons here
        //--------------------------------------------------------------------------------------------------------------
        val icons = arrayOfNulls<Int>(6)
        icons[0] = R.drawable.bebe
        icons[1] = R.drawable.comida
        icons[2] = R.drawable.ejercicios
        icons[3] = R.drawable.sintomas
        icons[4] = R.drawable.micronutrient
        icons[5] = R.drawable.calendario


        val adapter = MyAdapter(this, supportFragmentManager, 6)

        viewPager!!.adapter = adapter

        tabs.setupWithViewPager(viewPager)

        //set icons
        for (i in 0..adapter.count){
            tabs.getTabAt(i)?.setIcon(icons[i]!!)
        }

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))

        tabs!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        //--------------------------------------------------------------------------------------------------------------
        // end of tabs and icons

        //muestra datos en el head del drawer
        //--------------------------------------------------------------------------------------------------------------
        var userName: String
        var userEmail : String

        if(firebaseUser!= null){
            userName = firebaseUser?.displayName.toString()
            userEmail = firebaseUser?.email.toString()
            val verified = firebaseUser!!.isEmailVerified

            val userAux = firebaseUser

            if(userName.isNotEmpty()){
                userNameTextView.text = userName
            }
            if(userEmail.isNotEmpty()){
                userEmailTextView.text = userEmail
            }
            if(verified){
                correoVerificadoTextView.text = "Correo verificado"
            }
            else{
                correoVerificadoTextView.text = "Verificar correo"
                correoVerificadoTextView.setOnClickListener(object : View.OnClickListener{
                    override fun onClick(p0: View?) {
                        userAux?.sendEmailVerification()?.addOnCompleteListener{ task ->
                            if(task.isSuccessful){
                                Toast.makeText(applicationContext, "Correo enviado exitosamente", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                Toast.makeText(applicationContext, task.exception?.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }
        //--------------------------------------------------------------------------------------------------------------
    }

    fun updateUI(user: FirebaseUser?){
        if(user != null){
            val intent = Intent(this@MainDrawerActivity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_drawer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_profile -> {
                val intent = Intent(this@MainDrawerActivity, ProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_logout -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this@MainDrawerActivity, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
