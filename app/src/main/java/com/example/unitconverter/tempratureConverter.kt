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
import androidx.core.widget.doOnTextChanged

import android.view.LayoutInflater
import android.widget.Button

import android.widget.LinearLayout



class tempratureConverter : AppCompatActivity(), AdapterView.OnItemSelectedListener {




    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var ep1: EditText
    private lateinit var ep2: EditText
    var weight = arrayOf<String?>("Celsius", "Farenheight",
        "Kelvin"
    )


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_temprature_converter)



        spinner1=findViewById(R.id.Tempsp1)
        spinner2=findViewById(R.id.Tempsp2)
        ep1=findViewById(R.id.upperTemp)
        ep2=findViewById(R.id.LowerTemp)
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

                val convertedUnit=convertTempUnit(amt,spinner1.selectedItem.toString(),spinner2.selectedItem.toString())
                ep2.setText(convertedUnit.toString())
            }

        }
        ep2.doOnTextChanged {_,_,_,_->
            if(ep2.isFocused){
                val amt=
                    if(ep2.text.isEmpty()) 0.0
                    else ep2.text.toString().toDouble()

                val convertedUnit=convertTempUnit(amt,spinner2.selectedItem.toString(),spinner1.selectedItem.toString())
                ep1.setText(convertedUnit.toString())
            }

        }

        var b1:ImageButton=findViewById(R.id.back3);

        title = "KotlinApp"

        b1.setOnClickListener {
            val intent = Intent(this@tempratureConverter, MainActivity2::class.java)
            startActivity(intent)
        }



    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent!!.id){
            R.id.Tempsp1->{
                val amt=
                    if(ep1.text.isEmpty()) 0.0
                    else ep1.text.toString().toDouble()

                val convertedUnit=convertTempUnit(amt,spinner1.selectedItem.toString(),spinner2.selectedItem.toString())
                ep2.setText(convertedUnit.toString())
            }
            R.id.Tempsp2 ->{
                val amt=
                    if(ep2.text.isEmpty()) 0.0
                    else ep2.text.toString().toDouble()
                val convertedUnit=convertTempUnit(amt,spinner2.selectedItem.toString(),spinner1.selectedItem.toString())
                ep1.setText(convertedUnit.toString())
            }


        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


    private fun convertTempUnit(amt:Double, first:String,second:String):Double{
        val k=convertTempToCel(amt,first)
        return convertCelToTemp(k,second)
    }



    private fun convertTempToCel(amt:Double, first:String):Double{
   var ans:Double=0.0;
        when (first) {

            "Kelvin" -> ans=amt -273.0
            else -> ans= amt+ 0.0;
        }
        if(first=="Farenheight"){
            ans=(amt-32)*5/9
        }
        return ans
    }
    private fun convertCelToTemp(amt:Double, second:String):Double{
        var ans:Double=0.0;
        when (second) {

            "Kelvin" -> ans=amt +273.0
            else -> ans= amt+ 0.0;
        }
        if(second=="Farenheight"){
            ans=(amt*9/5)+32
        }
        return ans
    }






}

