package com.example.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelevtedDate : TextView? = null
    private var tvAgeInMin : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datepicker: Button = findViewById(R.id.btnDatePicker)

        tvSelevtedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMin = findViewById(R.id.tvAgeInMin)

        datepicker.setOnClickListener {
            clickDatePicker()
            // Toast.makeText(this, "Date picker works" , Toast.LENGTH_SHORT).show()
        }
    }

    private fun clickDatePicker() {

    val myCalendar = Calendar.getInstance()
    val year = myCalendar.get(Calendar.YEAR)
    val month = myCalendar.get(Calendar.MONTH)
    val day = myCalendar.get(Calendar.DAY_OF_MONTH)

    val dpd = DatePickerDialog(
        this,
        { _, selectedyear, selectedmonth, selecteddayOfMonth ->
            Toast.makeText(this, "Year is $selectedyear, Month is ${selectedmonth+1}, Day is $selecteddayOfMonth" , Toast.LENGTH_SHORT).show()

            val selectedDate = "$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"
            tvSelevtedDate?.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            theDate?.let {
                val selectedDateInMinute = theDate.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currentDateTimeInMinutes = currentDate.time / 60000
                    val differenceInMinutes = currentDateTimeInMinutes - selectedDateInMinute
                    tvAgeInMin?.text  = differenceInMinutes.toString()
                }
            }
        },
        year,
        month,
        day
    )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}