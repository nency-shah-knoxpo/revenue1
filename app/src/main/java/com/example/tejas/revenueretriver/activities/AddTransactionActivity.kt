package com.example.tejas.revenueretriver.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.tejas.revenueretriver.R
import com.example.tejas.revenueretriver.fragments.AddTransactionFragment

class AddTransactionActivity : SingleFragmentActivity() {
    override fun getFragment(): Fragment {

        return AddTransactionFragment()
    }


}
