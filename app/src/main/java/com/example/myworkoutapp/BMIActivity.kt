package com.example.myworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myworkoutapp.databinding.ActivityBmiactiviyBinding
import kotlinx.android.synthetic.main.activity_bmiactiviy.*
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {
    companion object{
        private const val METRIC_UNITS_VIEW = "METRIC_UNITS_VIEW"
        private const val US_UNITS_VIEW = "US_UNITS_VIEW"
    }

    private var binding: ActivityBmiactiviyBinding?= null

    private var currentVisibleView: String = METRIC_UNITS_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactiviyBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        makeMetricUnitVisible()


        binding?.rgUnits?.setOnCheckedChangeListener{_, checkedId: Int ->

            if (checkedId == R.id.rbMetricUnits){
                makeMetricUnitVisible()
            }else{
                makeUSUnitVisible()
            }
        }

        binding?.btnCalculateUnits?.setOnClickListener {
            calculateUnits()
        }
    }

    private fun makeMetricUnitVisible(){
        currentVisibleView = METRIC_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.VISIBLE
        binding?.tilUSMetricUnitWeight?.visibility = View.GONE
        binding?.tilMetricUnitFeet?.visibility = View.GONE
        binding?.tilMetricUnitHeightInch?.visibility=View.GONE

        binding?.etMetricUnitWeight!!.text!!.clear()
        binding?.etMetricUnitHeight!!.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }
    private fun makeUSUnitVisible(){
        currentVisibleView = US_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility = View.INVISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.INVISIBLE
        binding?.tilUSMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUnitFeet?.visibility = View.VISIBLE
        binding?.tilMetricUnitHeightInch?.visibility=View.VISIBLE

        binding?.etMetricUnitWeight!!.text!!.clear()
        binding?.etMetricUnitHeight!!.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun displayBMIResult(bmi: Float){

        val bmiLabel : String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0){
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more"
        }
        else if (bmi.compareTo(15f) <0 && bmi.compareTo(16f) <= 0){
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more"
        }
        else if (bmi.compareTo(16f) >0 && bmi.compareTo(18.5f) <= 0){
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more"
        }
        else if (bmi.compareTo(18.5f) >0 && bmi.compareTo(25f) <= 0){
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape"
        }
        else if (bmi.compareTo(25f) >0 && bmi.compareTo(30f) <= 0){
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take better care of yourself! WorkOut"
        }
        else if (bmi.compareTo(30f) >0 && bmi.compareTo(35f) <= 0){
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take better care of yourself! Workout"
        }
        else if (bmi.compareTo(35f) >0 && bmi.compareTo(40f) <= 0){
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "Oops! You really need to take better care of yourself! Workout"
        }
        else{
            bmiLabel = "Obese Class ||| (Moderately obese)"
            bmiDescription = "Omg! You are in a very dangerous condition! Act now!"
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding?.llDisplayBMIResult?.visibility=View.VISIBLE
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text= bmiLabel
        binding?.tvBMIDescription?.text= bmiDescription
    }

    private fun validateMetricUnits():Boolean{
        var isValid = true

        if (binding?.etMetricUnitWeight?.text.toString().isEmpty()){
            isValid = false
        }else if(binding?.etMetricUnitHeight?.text.toString().isEmpty()){

        }

        return isValid
    }

    private fun calculateUnits(){
        if (currentVisibleView == METRIC_UNITS_VIEW){
            if (validateMetricUnits()){
                val heightValue : Float = binding?.etMetricUnitHeight?.text.toString().toFloat() / 100
                val weightValue : Float = binding?.etMetricUnitWeight?.text.toString().toFloat()
                val bmi = weightValue / (heightValue*heightValue)

                displayBMIResult(bmi)
            }
            else{
                Toast.makeText(this@BMIActivity, "Please enter valid values.",
                    Toast.LENGTH_SHORT).show()
            }
        }
        else{
            if (validateUSUnits()){
                val usUnitHeightValueFeet: String =
                    binding?.etMetricUnitHeightFeet?.text.toString()
                val usUnitHeightValueInch: String =
                    binding?.etMetricUnitHeightInch?.text.toString()
                val usUnitWeightValue: Float =
                    binding?.etUSMetricUnitWeight?.text.toString().toFloat()

                val heightValue = usUnitHeightValueInch.toFloat() + usUnitHeightValueFeet.toFloat() * 12
                val bmi = 703 * (usUnitWeightValue / (heightValue * heightValue))
                displayBMIResult(bmi)
            }
            else{
                Toast.makeText(this@BMIActivity, "Please enter valid values.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateUSUnits():Boolean{
        var isValid = true

        when {
            binding?.etUSMetricUnitWeight?.text.toString().isEmpty() -> {
                isValid = false
            }
            binding?.etMetricUnitHeightFeet?.text.toString().isEmpty() -> {
                isValid = false
            }
            binding?.etMetricUnitHeightInch?.text.toString().isEmpty() -> {
                isValid = false
            }
        }
        return isValid
        }



    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}