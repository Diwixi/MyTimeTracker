package com.diwixis.mytimetracker

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*

class TimerViewHolder(private val rootView: View) {

    private val timerRecyclerAdapter by lazy { TimerRecyclerAdapter() }
    private var items: MutableList<TimerNote> = mutableListOf()

    var onCreateDialog: (() -> Unit)? = null

    fun setup(init: TimerViewHolder.() -> Unit): TimerViewHolder {
        init()

        with(rootView) {
            timers.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = timerRecyclerAdapter
            }

            addItem.setOnClickListener {
                onCreateDialog?.invoke()
            }
        }

        return this
    }

    fun addItem(itemTitle: String) {
        items.add(TimerNote(Date().time, 0, itemTitle))
        timerRecyclerAdapter.submitList(items)
        timerRecyclerAdapter.notifyDataSetChanged()
    }

}
