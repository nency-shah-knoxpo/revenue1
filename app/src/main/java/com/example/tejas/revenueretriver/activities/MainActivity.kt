package com.example.tejas.revenueretriver.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import com.example.tejas.revenueretriver.R
import com.example.tejas.revenueretriver.fragments.RevenueRetrieverFragment
import com.example.tejas.revenueretriver.fragments.SignInFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabPagerAdapter = TabPagerAdapter(supportFragmentManager)
        tabPagerAdapter.addFragment(RevenueRetrieverFragment(),"Menu")
        tabPagerAdapter.addFragment(SignInFragment(),"Calender")
        tabPagerAdapter.addFragment(SignInFragment(),"Settings")

        viewPager.adapter = tabPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

    }


    class TabPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

        private val fragments = ArrayList<Fragment>()
        private val titles = ArrayList<String>()

        fun addFragment(fragment: Fragment, title: String) {
            fragments.add(fragment)
            titles.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }


        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

    }



}
