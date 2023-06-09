package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.get
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    fun calculateTip(){
        val stringInTextField = binding.costOfServiceEditText.editText?.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if(cost == null){
            binding.tipResult.text=""
            Toast.makeText(this,"Enter Value",Toast.LENGTH_SHORT).show()
            return
        }


        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedId){
            R.id.option_twenty -> 0.20
            R.id.option_fifteen -> 0.15
            R.id.option_eighteen -> 0.18
            else -> 0.10
        }
        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked
        if(roundUp) {
            tip=kotlin.math.ceil(tip)
        }
        displayTip(tip)

    }

    private fun displayTip(tip: Double){
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text=getString(R.string.tip_amount,formattedTip)
    }

}