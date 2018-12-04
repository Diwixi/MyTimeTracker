package com.diwixis.mytimetracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class TimerRecyclerAdapter : ListAdapter<TimerNote, ItemTimerViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTimerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_time_tracker, parent, false)
        return ItemTimerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemTimerViewHolder, position: Int) {
        getItem(position).let { item ->
            holder.apply {
                bind(item)
            }
        }
    }

    private companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TimerNote>() {
            override fun areItemsTheSame(oldItem: TimerNote, newItem: TimerNote): Boolean {
                return areContentsTheSame(oldItem, newItem)
            }

            override fun areContentsTheSame(oldItem: TimerNote, newItem: TimerNote): Boolean {
                return (oldItem.startTime == newItem.startTime) &&
                        (oldItem.endTime == newItem.endTime) &&
                        (oldItem.description == newItem.description)
            }
        }
    }
}
