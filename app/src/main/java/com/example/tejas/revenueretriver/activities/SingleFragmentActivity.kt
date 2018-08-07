package com.example.tejas.revenueretriver.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.tejas.revenueretriver.R


abstract class SingleFragmentActivity : AppCompatActivity() {

    abstract fun getFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentLayoutId())
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (fragment == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, getFragment())
                    .commit()
        }
    }

    open fun getContentLayoutId(): Int {
        return R.layout.activity_single_fragment
    }

    val containedFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.fragment_container)
}