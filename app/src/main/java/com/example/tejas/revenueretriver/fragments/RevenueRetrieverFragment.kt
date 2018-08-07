package com.example.tejas.revenueretriver.fragments

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tejas.revenueretriver.ListFragment
import com.example.tejas.revenueretriver.R
import com.example.tejas.revenueretriver.Revenue

class RevenueRetrieverFragment : ListFragment<Revenue, RevenueRetrieverFragment.RevenueHolder>() {

    private val revenueItems: MutableList<Revenue> = mutableListOf()
    override fun onCreateViewHolder(view: View, viewType: Int): RevenueHolder {

        return RevenueHolder(view)
    }

    override fun onBindViewHolder(holder: RevenueHolder, item: Revenue?) {
        holder.bind(item!!)

    }

    override fun getItemLayoutId(viewType: Int): Int {
        return R.layout.item_revenue
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        for (i in 0..4) {
            val revenue = Revenue()
            revenue.revenueName = "James Cameron" + i
            revenue.revenueDescription = "Description" + i
            revenue.recurrence = "Monthly"
            revenue.amount = "$"+i+"00.00"
            revenueItems.add(i, revenue)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        addItemsAfterFetch(revenueItems)
        return view
    }

    inner class RevenueHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameTV = itemView.findViewById<TextView>(R.id.revenueNameTV)
        private val descriptionTV = itemView.findViewById<TextView>(R.id.revenueDescriptionTV)
        private val amountTV = itemView.findViewById<TextView>(R.id.revenueAmountTV)
        private val recurrenceTV = itemView.findViewById<TextView>(R.id.revenueRecurrenceTV)

        fun bind(revenue: Revenue) {

            nameTV.text = revenue.revenueName
            descriptionTV.text = revenue.revenueDescription
            amountTV.text = revenue.amount
            recurrenceTV.text = revenue.recurrence
        }
    }


}