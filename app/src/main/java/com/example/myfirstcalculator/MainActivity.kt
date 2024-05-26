package com.example.myfirstcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MainActivity : AppCompatActivity() {
   private var tvdateselector:TextView?=null
    private var tvdageinmin:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btndate:Button=findViewById(R.id.buttondatepicker)
        tvdateselector=findViewById(R.id.tvdate)
        tvdageinmin=findViewById(R.id.tvminutes)
        btndate.setOnClickListener {
            clickdate()

        }
    }
    private fun clickdate(){
        val mycalander= Calendar.getInstance()
        val year=mycalander.get(Calendar.YEAR)
        val month=mycalander.get(Calendar.MONTH)
        val day=mycalander.get(Calendar.DAY_OF_MONTH)
        val dpd=DatePickerDialog(this,

            DatePickerDialog.OnDateSetListener{view,year,month,dayofmonth ->
                Toast.makeText(this, "Year:$year  month:${month+1} day of month :${dayofmonth} ,", Toast.LENGTH_SHORT).show()
                val selecteddate="$dayofmonth/${month+1}/$year"
                ""
                tvdateselector?.text = selecteddate
                val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val thedate=sdf.parse(selecteddate)

                    val selectdate=thedate.time/60000


                val currentdate=sdf.parse(sdf.format(System.currentTimeMillis()))
                currentdate?.let {
                    val currentdatekknminute= currentdate.time/60000
                    val diff=currentdatekknminute-selectdate
                    tvdageinmin?.text=diff.toString()
                }


            },year ,
            month,
            day
        )
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()
        //DatePickerDialog()

    }
}