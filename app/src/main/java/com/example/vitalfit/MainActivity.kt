package com.example.vitalfit

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    private var isMaleSelected : Boolean = true
    private var isFemaleSelected : Boolean = false
    private var currentWeight : Int = 70
    private var currentAge : Int = 25
    private var currentHeight : Int = 120

    // Gender Components
    private lateinit var viewMale : CardView
    private lateinit var viewFemale : CardView

    // Height Components
    private lateinit var tvHeightValue : TextView
    private lateinit var rsHeight : RangeSlider

    // Weight Components
    private lateinit var btnMinusWeight : FloatingActionButton
    private lateinit var btnAddWeight : FloatingActionButton
    private lateinit var tvWeightValue : TextView

    // Age Components
    private lateinit var btnMinusAge : FloatingActionButton
    private lateinit var btnAddAge : FloatingActionButton
    private lateinit var tvAgeValue : TextView

    // Calculate Button
    private lateinit var btnCalculate : Button

    companion object{
        const val IMC_KEY = "IMC_RESULT"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Screen Orientation
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        initComponent()
        initListener()
        initUI()

    }

    //Initialize components
    private fun initComponent() {
        viewMale = findViewById(R.id.card1)
        viewFemale = findViewById(R.id.card2)
        //Height Component
        tvHeightValue = findViewById(R.id.tvHeightValue)
        rsHeight = findViewById(R.id.rsHeight)
        //Weight Component
        btnMinusWeight = findViewById(R.id.btnMinusWeight)
        btnAddWeight = findViewById(R.id.btnAddWeight)
        tvWeightValue = findViewById(R.id.tvWeightValue)
        //Age Component
        btnMinusAge = findViewById(R.id.btnMinusAge)
        btnAddAge = findViewById(R.id.btnAddAge)
        tvAgeValue = findViewById(R.id.tvAgeValue)
        //Calculate Button
        btnCalculate = findViewById(R.id.btnCalculate)

    }

    //Accede a cada vista
    @SuppressLint("SetTextI18n")
    private fun initListener() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }

        rsHeight.addOnChangeListener{ _, value, _ ->
            val decFormat = DecimalFormat("#.##")
            currentHeight = decFormat.format(value).toInt()
            tvHeightValue.text = "$currentHeight cm"
        }

        btnCalculate.setOnClickListener{
            val result = calculateIMC()
            navigateToResultActivity(result)
        }

        btnMinusWeight.setOnClickListener{
            currentWeight -= 1
            setWeight()
        }
        btnAddWeight.setOnClickListener{
            currentWeight += 1
            setWeight()
        }
        btnMinusAge.setOnClickListener{
            currentAge -= 1
            setAge()
        }
        btnAddAge.setOnClickListener{
            currentAge += 1
            setAge()
        }
    }

    private fun navigateToResultActivity(result : Double){
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMC() : Double {
        val decFormat = DecimalFormat("#.##")
        val imc = currentWeight / (currentHeight.toDouble()/100 * currentHeight.toDouble()/100)
        return decFormat.format(imc).toDouble()
    }



    private fun setAge(){
        tvAgeValue.text = currentAge.toString()
    }

    private fun setWeight() {
        tvWeightValue.text = currentWeight.toString()
    }

    private fun changeGender(){
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean) : Int {
        val colorReference = if(isSelectedComponent){
            R.color.selected
        }else{
            R.color.is_selected
        }

        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }
}
