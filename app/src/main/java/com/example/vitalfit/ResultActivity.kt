package com.example.vitalfit

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {

    private lateinit var tvResultImc : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        initComponent()
        getExtra()
    }

    private fun initComponent(){
        tvResultImc = findViewById(R.id.tvResultImc)
    }

    private fun getExtra(){
        intent.putExtras(intent)
        val data = intent.getStringExtra("Imc")
        tvResultImc.text = data
    }
}
