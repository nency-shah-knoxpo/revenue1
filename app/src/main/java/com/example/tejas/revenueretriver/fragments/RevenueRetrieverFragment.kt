package com.example.tejas.revenueretriver.fragments

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tejas.revenueretriver.ListFragment
import com.example.tejas.revenueretriver.R
import com.example.tejas.revenueretriver.Revenue

class RevenueRetrieverFragment : ListFragment<Revenue, RevenueRetrieverFragment.RevenueHolder>() {
    override fun onCreateViewHolder(view: View, viewType: Int): RevenueHolder {

        return RevenueHolder(view)
    }

    override fun onBindViewHolder(holder: RevenueHolder, item: Revenue?) {
        holder.bind(item!!)

    }

    override fun getItemLayoutId(viewType: Int): Int {
        return R.layout.item_revenue
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        return view
    }

    inner class RevenueHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(revenue: Revenue) {


        }
    }


}