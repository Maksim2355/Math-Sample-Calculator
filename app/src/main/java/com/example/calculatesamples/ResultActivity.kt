package com.example.calculatesamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculatesamples.databinding.ActivityResultBinding
import com.example.calculatesamples.page_fragment.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.navigationToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.navigationToolbar.setNavigationOnClickListener { onBackPressed() }
        val viewStateAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = viewStateAdapter

        TabLayoutMediator(binding.tabsPageTl, binding.viewPager) { tab, position ->
            if (position == 0) {
                tab.text = "Graphs"
            }else {
                tab.text = "Data"
            }
        }.attach()
    }

}