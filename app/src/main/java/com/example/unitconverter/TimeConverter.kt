package com.example.unitconverter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged

class TimeConverter : AppCompatActivity(), AdapterView.OnItemSelectedListener {


    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var ep1: EditText
    private lateinit var ep2: EditText
    var weight = arrayOf<String?>("second",
        "minute","hour","day"
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_time_converter)


        spinner1=findViewById(R.id.tisp1)
        spinner2=findViewById(R.id.tisp2)
        ep1=findViewById(R.id.upperTim)
        ep2=findViewById(R.id.LowerTim)
        spinner1.onItemSelectedListener =this
        spinner2.onItemSelectedListener =this



        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            weight)
        val ad2: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            weight)

        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)
        ad2.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)

        spinner1.adapter = ad
        spinner2.adapter=ad2

        ep1.doOnTextChanged {_,_,_,_->
            if(ep1.isFocused){
                val amt=
                    if(ep1.text.isEmpty()) 0.0

                    else ep1.text.toString().toDouble()

                val convertedUnit=convertWeightUnit(amt,spinner1.selectedItem.toString(),spinner2.selectedItem.toString())
                ep2.setText(convertedUnit.toString())
            }

        }
        ep2.doOnTextChanged {_,_,_,_->
            if(ep2.isFocused){
                val amt=
                    if(ep2.text.isEmpty()) 0.0
                    else ep2.text.toString().toDouble()

                val convertedUnit=convertWeightUnit(amt,spinner2.selectedItem.toString(),spinner1.selectedItem.toString())
                ep1.setText(convertedUnit.toString())
            }

        }

        var b1: ImageButton =findViewById(R.id.back6);

        title = "KotlinApp"

        b1.setOnClickListener {
            val intent = Intent(this@TimeConverter, MainActivity2::class.java)
            startActivity(intent)
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        when(parent!!.id){
            R.id.sp1->{
                val amt=
                    if(ep1.text.isEmpty()) 0.0
                    else ep1.text.toString().toDouble()

                val convertedUnit=convertWeightUnit(amt,spinner1.selectedItem.toString(),spinner2.selectedItem.toString())
                ep2.setText(convertedUnit.toString())
            }
            R.id.sp2 ->{
                val amt=
                    if(ep2.text.isEmpty()) 0.0
                    else ep2.text.toString().toDouble()
                val convertedUnit=convertWeightUnit(amt,spinner2.selectedItem.toString(),spinner1.selectedItem.toString())
                ep1.setText(convertedUnit.toString())
            }


        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
    private fun convertWeightUnit(amt:Double, firstweight:String,secondWeight:String):Double{
        val k=convertWeightToKilo(amt,firstweight)
        return convertKiloToWeight(k,secondWeight)
    }



    private fun convertWeightToKilo(amt:Double, firstweight:String):Double{
        return amt* when(firstweight){
            "second" -> 1.0
            "minute" -> 60.0
            "hour"->   3600.0

            else->86400.0



        }
    }
    private fun convertKiloToWeight(amt:Double, secondWeight:String):Double {
        return amt * when (secondWeight) {
            "second" -> 1.0
            "minute" -> 1/60.0
            "hour"->   1/3600.0

            else->1/86400.0


        }
    }

}