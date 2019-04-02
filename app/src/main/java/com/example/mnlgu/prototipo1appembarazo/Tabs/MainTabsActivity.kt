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


        /*
        var main : TabLayout.Tab?= TabLayout.Tab()

        main!!.setText("Main")

        var dieta : TabLayout.Tab? = TabLayout.Tab()
        dieta!!.setText("Dieta")

        var ejercicios : TabLayout.Tab? = TabLayout.Tab()
        ejercicios!!.setText("Ejercicios")

        var sintomas : TabLayout.Tab? = TabLayout.Tab()
        sintomas!!.setText("Sintomas")

        var calendario : TabLayout.Tab? = TabLayout.Tab()
        calendario!!.setText("Main")

        tabLayout!!.addTab(main)
        tabLayout!!.addTab(dieta)
        tabLayout!!.addTab(ejercicios)
        tabLayout!!.addTab(sintomas)
        tabLayout!!.addTab(calendario)
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
        */

        val adapter = MyAdapter(this, supportFragmentManager, 5)
        viewPager!!.adapter = adapter

        tabs.setupWithViewPager(viewPager)

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