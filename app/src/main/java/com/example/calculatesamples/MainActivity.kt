package com.example.calculatesamples

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculatesamples.data.SampleData
import com.example.calculatesamples.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.stepIntervalCv.minCounterValue = 1
        binding.createSamplesBtn.setOnClickListener { v ->
            val startInterval = binding.startIntervalCv.counterNumb
            val stepInterval = binding.stepIntervalCv.counterNumb
            val textSamplingInput = binding.samplingInputEt.text.toString()
            val samples: List<Int> = if (textSamplingInput.isNotEmpty()) {
                textSamplingInput.split(' ').map { it.toInt() }
            } else emptyList()
            if (samples.isNotEmpty()) {
                val sampleData = SampleData(
                    startInterval,
                    stepInterval,
                    samples
                )
                val intentToActivityResult = Intent(this, ResultActivity::class.java)
                val bundle = Bundle().apply {
                    putParcelable(EXTRA_SAMPLING_DATA, sampleData)
                }
                startActivity(intentToActivityResult, bundle)
            } else {
                Snackbar.make(
                    binding.mainActivityRootLayout,
                    "Выборка не удовлетворяет условию",
                    Snackbar.LENGTH_SHORT
                ).setBackgroundTint(Color.BLUE).show()
            }
        }
    }
}