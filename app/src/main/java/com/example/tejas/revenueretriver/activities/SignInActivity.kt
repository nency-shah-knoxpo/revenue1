package com.example.tejas.revenueretriver.activities

import android.support.v4.app.Fragment
import com.example.tejas.revenueretriver.fragments.SignInFragment


class SignInActivity : SingleFragmentActivity() {
    override fun getFragment(): Fragment {
        return SignInFragment()
    }
}