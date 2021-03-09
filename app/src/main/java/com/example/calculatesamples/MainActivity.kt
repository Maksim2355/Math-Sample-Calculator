package com.example.calculatesamples

import GenerateData
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculatesamples.data.DataVariationsSeries
import com.example.calculatesamples.data.PointsGraphs
import com.example.calculatesamples.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createTrialSamplesBtn.setOnClickListener {
            createMockSamples()
        }
        binding.stepIntervalCv.minCounterValue = 1
        binding.createSamplesBtn.setOnClickListener { v ->
            val startInterval = binding.startIntervalCv.counterNumb
            val stepInterval = binding.stepIntervalCv.counterNumb
            val textSamplingInput = binding.samplingInputEt.text.toString()
            val samples: List<Int> = if (textSamplingInput.isNotEmpty()) {
                textSamplingInput.split(' ').map { it.toInt() }
            } else emptyList()
            if (samples.isNotEmpty()) {
                val data = GenerateData(startInterval, stepInterval, samples)
                navigateToResultActivity(data.variationsSeries, data.createDataGraphs())
            } else {
                showSnackBar()
            }
        }
    }

    private fun navigateToResultActivity(
        variationsSeries: DataVariationsSeries,
        pointsGraphs: PointsGraphs
    ) {
        val intentToActivityResult = Intent(this, ResultActivity::class.java)
        intentToActivityResult.putExtra(EXTRA_SAMPLING_POINTS, pointsGraphs)
        intentToActivityResult.putExtra(EXTRA_SAMPLING_DATA_SERIES, variationsSeries)

        startActivity(intentToActivityResult)
    }

    private fun createMockSamples() {
        val mockSamples: String = GenerateSamples.createMockSamples().joinToString(" ")
        binding.samplingInputEt.setText(mockSamples)
        binding.stepIntervalCv.changeCounter(2)
        binding.startIntervalCv.changeCounter(59)
    }


    private fun showSnackBar() {
        Snackbar.make(
            binding.mainActivityRootLayout,
            "Выборка не удовлетворяет условию",
            Snackbar.LENGTH_SHORT
        ).setBackgroundTint(Color.BLUE).show()
    }


}