package com.example.mnlgu.prototipo1appembarazo.MainActivities

import android.support.v4.app.FragmentPagerAdapter
import android.content.Context;
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class MyAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                return com.example.mnlgu.prototipo1appembarazo.Fragments.FragmentMain()
            }
            1 -> {
                return com.example.mnlgu.prototipo1appembarazo.Fragments.FragmentDieta()
            }
            2 -> {
                return com.example.mnlgu.prototipo1appembarazo.Fragments.FragmentEjercicios()
            }
            3 -> {
                return com.example.mnlgu.prototipo1appembarazo.Fragments.FragmentSintomas()
            }
            else -> {
                return com.example.mnlgu.prototipo1appembarazo.Fragments.FragmentMicronutrimentos()
            }
            /*
            else -> {
                return com.example.mnlgu.prototipo1appembarazo.Fragments.FragmentCalendario()
            }
            */
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}