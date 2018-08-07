package com.example.tejas.revenueretriver.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.example.tejas.revenueretriver.R
import com.example.tejas.revenueretriver.fragments.RevenueRetrieverFragment
import com.example.tejas.revenueretriver.fragments.SignInFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tabIcons = intArrayOf(R.drawable.home, R.drawable.calendar, R.drawable.settings)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabPagerAdapter = TabPagerAdapter(supportFragmentManager)
        tabPagerAdapter.addFragment(RevenueRetrieverFragment(),getString(R.string.menu))
        tabPagerAdapter.addFragment(SignInFragment(),getString(R.string.calender))
        tabPagerAdapter.addFragment(SignInFragment(),getString(R.string.settings))

        viewPager.adapter = tabPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        for (i in 0 until tabLayout.tabCount) {

            val tab = LayoutInflater.from(this).inflate(R.layout.item_tab, tabLayout, false)
            val tabIcon = tab.findViewById<ImageView>(R.id.tab_icon)
            tabIcon.setImageResource(tabIcons[i])
            tabLayout.getTabAt(i)!!.customView = tab
        }

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
