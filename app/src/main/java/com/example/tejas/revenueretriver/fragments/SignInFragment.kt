package com.example.tejas.revenueretriver.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.tejas.revenueretriver.activities.MainActivity
import com.example.tejas.revenueretriver.R

class SignInFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_sign_in, container, false)
       val signInBtn =  v.findViewById<Button>(R.id.signInBtn)
        signInBtn.setOnClickListener {
            var intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
        return v

    }
}