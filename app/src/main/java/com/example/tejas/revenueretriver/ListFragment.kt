package com.example.tejas.revenueretriver

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.LayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView



/**
 * Created by knoxpo on 13/03/18.
 */
abstract class ListFragment<T, VH : RecyclerView.ViewHolder> : Fragment() {



    companion object {

        private val DEFAULT_THRESHOLD = 15
        private val DEFAULT_VIEW_TYPE = 0

        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }
    interface OnListSwipeListener<E> {
        fun onSwiped(position: Int, direction: Int, item: E)
    }

    private val defaultViewType = 99999

    var items: MutableList<T> = mutableListOf()
        private set

    abstract fun onCreateViewHolder(view: View, viewType: Int): VH
    abstract fun onBindViewHolder(holder: VH, item: T?)
    abstract fun getItemLayoutId(viewType: Int): Int


    private var listAdapter: ListAdapter? = null
    protected val threshold: Int
        get() = DEFAULT_THRESHOLD

    private var emptyText: String? = null
    private var progressText: String? = null
    var isFetching = false
        private set

    private var itemListRV: RecyclerView? = null
    private var fetchingWrapperLL: LinearLayout? = null
    private var emptyTV: TextView? = null
    private var fetchingTV: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v =  inflater.inflate(getFragmentLayoutId(), container, false)
        init(v)

        return v
    }

    private fun init(v: View) {
        itemListRV = v.findViewById(R.id.itemListRV)
        fetchingWrapperLL = v.findViewById(R.id.fetchingWrapperLL)
        emptyTV = v.findViewById(R.id.emptyTV)
        fetchingTV = v.findViewById(R.id.fetchingTV)

        emptyText = getString(R.string.no_data)
        progressText = getString(R.string.please_wait)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        updateUI()
        val manager = getLayoutManager()

        itemListRV?.layoutManager = manager

        listAdapter = ListAdapter(activity)
        itemListRV?.adapter = listAdapter


        fetchItems()
    }

    private fun updateUI(shouldRebindList: Boolean = true) {
        fetchingWrapperLL?.visibility = if (isFetching && itemCount == 0) View.VISIBLE else View.GONE
        emptyTV?.visibility = if (!isFetching && itemCount <= 0) View.VISIBLE else View.GONE
        itemListRV?.visibility = if (itemCount > 0) View.VISIBLE else View.GONE

        if (shouldRebindList) {
            listAdapter?.notifyDataSetChanged()
        }

        emptyTV?.text = emptyText
        fetchingTV?.text = progressText
    }

    fun fetchItems() {
        isFetching = true
        updateUI()
        onFetchItems()
    }

    open fun onFetchItems() {
        completeFetch()
    }

    fun setEmptyText(text: String) {
        emptyText = text
        updateUI()
    }


    fun addItemsAfterFetch(newItems: List<T>?) {
        if (newItems != null) {
            items.addAll(newItems)
        }
        completeFetch()
    }

    fun updateItemsAfterFetch(newItems: List<T>?) {
        items.clear()
        if (newItems != null) {
            items.addAll(newItems)
        }
        completeFetch()
    }

    fun completeFetch() {
        isFetching = false
        updateUI()
    }


    open fun getLayoutManager(): LayoutManager {
        return LinearLayoutManager(activity)
    }

    open fun getItemType(item: T): Int {
        return defaultViewType
    }

    open fun getFragmentLayoutId(): Int {
        return R.layout.fragment_list
    }

    fun addItem(item: T) {
        items.add(item)
        listAdapter?.notifyItemInserted(items.size - 1)
    }

    fun addItem(item: T, position: Int){
        items.add(position, item)
        listAdapter?.notifyItemInserted(position)
        updateUI(false)
    }

    fun changeItem(item: T, position: Int){
        items[position] = item
        listAdapter?.notifyItemChanged(position)
    }

    fun removeItem(item: T) {
        val indexToRemove = items.indexOf(item)
        removeItem(indexToRemove)
        updateUI(false)
    }

    fun removeItem(indexToRemove: Int) {
        if (indexToRemove >= 0) {
            items.removeAt(indexToRemove)
            listAdapter?.notifyItemRemoved(indexToRemove)
        }
    }

    fun notifyItemChanged(index: Int) {
        listAdapter?.notifyItemChanged(index)
    }

    fun notifyItemInserted(index: Int) {
        var shouldUpdateUI = false
        if(itemCount == 0){
            shouldUpdateUI = true
        }
        listAdapter?.notifyItemInserted(index)

        updateUI(shouldUpdateUI)
    }

    fun notifyItemRemoved(position: Int) {
        listAdapter?.notifyItemRemoved(position)
    }

    var itemCount: Int = 0
        get() = listAdapter?.itemCount ?: 0

    protected open fun canItemSwipe(position: Int?): Boolean {
        return true
    }



    inner class ListAdapter(context: Context?) : RecyclerView.Adapter<VH>() {

        private val inflater = LayoutInflater.from(context)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            val view = inflater.inflate(getItemLayoutId(viewType), parent, false)
            return onCreateViewHolder(view, viewType)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            onBindViewHolder(holder, items[position])
        }

        override fun getItemViewType(position: Int): Int {
            return getItemType(items[position])
        }
    }

}

