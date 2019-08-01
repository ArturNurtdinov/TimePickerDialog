package com.example.timepickerdialot

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    private lateinit var calendar: Calendar
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        calendar = Calendar.getInstance()

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        return TimePickerDialog(
                activity,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth,
                this,
                hour,
                minute,
                false // it means we use AM/PM format
        )
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val tv:TextView = activity?.findViewById(R.id.text_view) as TextView
        tv.text = "${getHourAMPM(hourOfDay)}:$minute ${getAMPM(hourOfDay)}"
    }

    override fun onCancel(dialog: DialogInterface?) {
        Toast.makeText(activity, "Picker Canceled.", Toast.LENGTH_SHORT).show()
        super.onCancel(dialog)
    }

    private fun getAMPM(hour: Int): String {
        return if (hour > 11) "PM" else "AM"
    }

    private fun getHourAMPM(hour: Int): Int {
        var modifiedHour = if (hour > 11) hour - 12 else hour
        if (modifiedHour == 0) {
            modifiedHour = 12
        }
        return modifiedHour
    }
}