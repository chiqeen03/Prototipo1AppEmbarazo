package com.example.mnlgu.prototipo1appembarazo.Tabs

import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity

import android.os.Bundle
import android.support.v4.view.ViewPager
import com.example.mnlgu.prototipo1appembarazo.R
import kotlinx.android.synthetic.main.activity_main_tabs.*

class MainTabsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tabs)


        setSupportActionBar(toolbar) //set toolbar

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

    }
}