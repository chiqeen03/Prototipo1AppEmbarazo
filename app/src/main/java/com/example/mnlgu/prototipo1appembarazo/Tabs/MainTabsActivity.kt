package com.example.mnlgu.prototipo1appembarazo.Tabs

import android.net.Uri
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity

import android.os.Bundle
import android.support.v4.view.ViewPager
import com.example.mnlgu.prototipo1appembarazo.R
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main_tabs.*
import com.google.firebase.storage.StorageReference
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.storage.UploadTask
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_main_tabs.*
import java.io.File


class MainTabsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tabs)

        setSupportActionBar(toolbar) //set toolbar

        //tabs and icons here
        //------------------------------------------------------------------------------
        val icons = arrayOfNulls<Int>(5)
        icons[0] = R.drawable.bebe
        icons[1] = R.drawable.comida
        icons[2] = R.drawable.ejercicios
        icons[3] = R.drawable.sintomas
        icons[4] = R.drawable.calendario

        val adapter = MyAdapter(this, supportFragmentManager, 5)
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

        //------------------------------------------------------------------------------
        //end of tabs and icons

        //database
        //------------------------------------------------------------------------------
        val db : FirebaseFirestore = FirebaseFirestore.getInstance()

        /*
        var user : MutableMap<String, Any> = HashMap()
        user.put("first", "Ada")
        user.put("last", "Lovelace")
        user.put("born", 1815)

        db.collection("users").add(user)
        */
        //------------------------------------------------------------------------------
        //end of database
    }
}