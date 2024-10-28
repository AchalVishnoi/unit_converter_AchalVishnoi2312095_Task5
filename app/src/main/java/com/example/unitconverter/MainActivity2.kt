package com.example.unitconverter

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.view.LayoutInflater
import android.widget.LinearLayout

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }


        var b1:LinearLayout=findViewById(R.id.WeightImg);

        title = "KotlinApp"

        b1.setOnClickListener {
            val intent = Intent(this@MainActivity2, weightConverter::class.java)
            startActivity(intent)
        }

        var b2:LinearLayout=findViewById(R.id.volImg);


        b2.setOnClickListener {
            val intent = Intent(this@MainActivity2, volumeConverter::class.java)
            startActivity(intent)
        }
        var b3:LinearLayout=findViewById(R.id.TempImg);


        b3.setOnClickListener {
            val intent = Intent(this@MainActivity2, tempratureConverter::class.java)
            startActivity(intent)
        }
        var b4:LinearLayout=findViewById(R.id.lenImg);


        b4.setOnClickListener {
            val intent = Intent(this@MainActivity2, LengthConverter::class.java)
            startActivity(intent)
        }


    }
}