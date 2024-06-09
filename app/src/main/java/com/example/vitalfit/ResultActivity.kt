package com.example.vitalfit

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.vitalfit.MainActivity.Companion.IMC_KEY

class ResultActivity : AppCompatActivity() {

    private lateinit var imcStatus: TextView
    private lateinit var imcResultValue: TextView
    private lateinit var imcDesc: TextView
    private lateinit var btnReCalculate: Button

    companion object {
        const val ERROR_MSG = "INVALID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val result = intent.extras?.getDouble(IMC_KEY) ?: -1.0

        initComponent()
        initUI(result)
        initListener()
    }

    private fun initComponent() {
        imcStatus = findViewById(R.id.imcStatus)
        imcResultValue = findViewById(R.id.imcResultValue)
        imcDesc = findViewById(R.id.imcDesc)
        btnReCalculate = findViewById(R.id.btnReCalculate)
    }

    private fun initUI(result: Double) {
        when (result) {

            in 0.00..18.50 -> {
                imcStatus.text = getString(R.string.underweight)
                imcStatus.setTextColor(ContextCompat.getColor(this, R.color.underweight))
                imcResultValue.text = result.toString()
                imcDesc.text= getString(R.string.underweight_desc)
            }

            in 18.51..24.99 -> {
                imcStatus.text = getString(R.string.normal)
                imcStatus.setTextColor(ContextCompat.getColor(this, R.color.normal))
                imcResultValue.text = result.toString()
                imcDesc.text= getString(R.string.normal_desc)
            }

            in 25.00..29.99 -> {
                imcStatus.text = getString(R.string.overweight)
                imcStatus.setTextColor(ContextCompat.getColor(this, R.color.overweight))
                imcResultValue.text = result.toString()
                imcDesc.text = getString(R.string.overweight_desc)
            }

            in 30.00..99.00 -> {
                imcStatus.text = getString(R.string.obese)
                imcStatus.setTextColor(ContextCompat.getColor(this, R.color.obese))
                imcResultValue.text = result.toString()
                imcDesc.text = getString(R.string.obese_desc)
            }
            else -> {
                imcStatus.text = ERROR_MSG
                imcResultValue.text = ERROR_MSG
                imcDesc.text = ERROR_MSG
            }
        }
    }

    private fun initListener(){
        btnReCalculate.setOnClickListener {
            onBackPressedDispatcher
        }
    }


}