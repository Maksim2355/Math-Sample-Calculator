package com.example.calculatesamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculatesamples.data.SampleData
import com.example.calculatesamples.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        binding?.let { bindingNotNull ->
            bindingNotNull.stepIntervalCv.minCounterValue = 1
            bindingNotNull.createSamplesBtn.setOnClickListener { v ->
                val startInterval = bindingNotNull.startIntervalCv.counterNumb
                val stepInterval = bindingNotNull.stepIntervalCv.counterNumb
                val samples = bindingNotNull.samplingInputEt.text.toString().split(' ').map {
                    it.toInt()
                }
                val sampleData = SampleData(
                    bindingNotNull.startIntervalCv.counterNumb,
                    bindingNotNull.stepIntervalCv.counterNumb,
                    bindingNotNull.samplingInputEt.text.toString().split(' ').map { it.toInt() }
                )

            }
        }

    }

    private fun errorProcessing(){

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}