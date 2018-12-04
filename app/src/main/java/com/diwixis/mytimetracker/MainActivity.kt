package com.diwixis.mytimetracker

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_timer.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewHolder: TimerViewHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewHolder = TimerViewHolder(rootView = rootTimerView).setup {
            onCreateDialog = {
                createDialog()
            }
        }
    }

    private fun createDialog() {
        val layout = layoutInflater.inflate(R.layout.dialog_add_timer, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(layout)
            .setPositiveButton("OK") { dialog, _->
                viewHolder.addItem(layout.timerItemTitle.text.toString())
                dialog.dismiss()
            }.create().show()
    }
}
