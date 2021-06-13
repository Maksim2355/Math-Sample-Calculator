package com.example.calculatesamples

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculatesamples.data.DataVariationsSeries
import com.example.calculatesamples.data.PointsGraphs
import com.example.calculatesamples.databinding.ActivityMainBinding
import com.example.calculatesamples.logic.GenerateData
import com.example.calculatesamples.logic.calculate
import com.example.calculatesamples.logic.calculateWithCustomStep
import com.example.calculatesamples.utils.*
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createTrialSamplesBtn.setOnClickListener { createMockSamples() }
        binding.stepIntervalCv.minCounterValue = 1
        binding.samplingInputEt.setOnSampleInputFilter()
        binding.createSamplesBtn.setOnClickListener {
            val startInterval = binding.startIntervalCv.counterNumb
            val stepInterval = binding.stepIntervalCv.counterNumb
            val samples: List<Float> = mapToSample(binding.samplingInputEt.text.toString())
            if (samples.isNotEmpty()) {
                navigateToResultActivity(
                    if (stepInterval == 1) {
                        samples.calculate(startInterval)
                    } else {
                        samples.calculateWithCustomStep(startInterval, stepInterval)
                    }
                )
            } else {
                showSnackBar()
            }
        }
    }

    private fun navigateToResultActivity(
        variationsSeries: DataVariationsSeries,
    ) {
        val intentToActivityResult = Intent(this, ResultActivity::class.java)
        intentToActivityResult.putExtra(EXTRA_SAMPLING_DATA_SERIES, variationsSeries)
        startActivity(intentToActivityResult)
    }

    private fun createMockSamples() {
        val mockSamples: String = GenerateSamples.createMockSamples().joinToString(" ")
        binding.samplingInputEt.setText(mockSamples)
    }

    private fun showSnackBar() {
        Snackbar.make(
            binding.mainActivityRootLayout,
            "Выборка не удовлетворяет условию",
            Snackbar.LENGTH_SHORT
        ).setBackgroundTint(Color.BLUE).show()
    }


}