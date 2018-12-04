package com.diwixis.mytimetracker

import android.app.TimePickerDialog
import android.icu.text.DateFormat
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_time_tracker.view.*
import java.util.*

class ItemTimerViewHolder(private val rootView: View) : RecyclerView.ViewHolder(rootView) {

    fun bind(item: TimerNote) {
        bindStartTimer(item)
        bindEndTimer(item)
        fieldData(item)
    }

    private fun bindStartTimer(item: TimerNote) {
        with(itemView) {
            startTimerButton.setOnClickListener {
                item.startTime = Date().time
                startTimeEt.text = DateFormat.getTimeInstance(DateFormat.SHORT).format(Date(item.startTime))
                fieldDifference(item)
            }

            startTimeEt.setOnClickListener {
                val timePickerDialog = TimePickerDialog(rootView.context,
                    TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                        val calendar = Calendar.getInstance()
                        calendar.set(calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DATE),
                            hourOfDay,
                            minute)

                        item.startTime = calendar.timeInMillis

                        startTimeEt.text = DateFormat.getTimeInstance(DateFormat.SHORT).format(Date(item.startTime))
                        fieldDifference(item)
                    }, Date(item.startTime).hours, Date(item.startTime).minutes, false)
                timePickerDialog.show()
            }

        }
    }

    private fun bindEndTimer(item: TimerNote) {
        with(itemView) {
            endTimerButton.setOnClickListener {
                item.endTime = Date().time
                endTimeEt.text = DateFormat.getTimeInstance(DateFormat.SHORT).format(Date(item.endTime))
                fieldDifference(item)
            }


            endTimeEt.setOnClickListener {
                val timePickerDialog = TimePickerDialog(rootView.context,
                    TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                        val calendar = Calendar.getInstance()
                        calendar.set(calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DATE),
                            hourOfDay,
                            minute)

                        item.endTime = calendar.timeInMillis

                        endTimeEt.text = DateFormat.getTimeInstance(DateFormat.SHORT).format(Date(item.endTime))

                        fieldDifference(item)
                    }, Date(item.endTime).hours, Date(item.endTime).minutes, false)
                timePickerDialog.show()
            }
        }
    }

    private fun fieldDifference(item: TimerNote) {
        with(itemView) {
            if (item.startTime != 0L && item.endTime != 0L) {
                differenceTime.text = ((item.endTime - item.startTime) / (60 * 1000)).toString()
            } else {
                differenceTime.text = "0"
            }
        }
    }

    private fun fieldData(item: TimerNote) {
        with(itemView) {
            if (item.description.isNotEmpty()) timeTitle.text = item.description
            if (item.startTime > 0) startTimeEt.text = DateFormat.getTimeInstance(DateFormat.SHORT).format(Date(item.startTime))
            if (item.endTime > 0) endTimeEt.text = DateFormat.getTimeInstance(DateFormat.SHORT).format(Date(item.endTime))
            if (item.startTime > 0 && item.endTime > 0) differenceTime.text = ((item.endTime - item.startTime) / (60 * 1000)).toString()
        }
    }

}
