package com.example.calculatesamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculatesamples.data.DataVariationsSeries
import com.example.calculatesamples.data.PointsGraphs
import com.example.calculatesamples.databinding.ActivityResultBinding
import com.example.calculatesamples.page_fragment.ViewPagerAdapter
import com.example.calculatesamples.utils.EXTRA_SAMPLING_DATA_SERIES
import com.example.calculatesamples.utils.EXTRA_SAMPLING_POINTS
import com.google.android.material.tabs.TabLayoutMediator

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    private var pointsGraphs: PointsGraphs? = null
    private var paramVariationSeriesFragment: DataVariationsSeries? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.navigationToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.navigationToolbar.setNavigationOnClickListener { onBackPressed() }
        paramVariationSeriesFragment =
            intent.getSerializableExtra(EXTRA_SAMPLING_DATA_SERIES) as DataVariationsSeries
        pointsGraphs =
            intent.getSerializableExtra(EXTRA_SAMPLING_POINTS) as PointsGraphs

        val viewStateAdapter = ViewPagerAdapter(
            paramVariationSeriesFragment!! to pointsGraphs!!,
            supportFragmentManager,
            lifecycle
        )
        binding.viewPager.adapter = viewStateAdapter
        TabLayoutMediator(binding.tabsPageTl, binding.viewPager) { tab, position ->
            if (position == 0) {
                tab.text = "Result"
            } else {
                tab.text = "Graph"
            }
        }.attach()
    }

}