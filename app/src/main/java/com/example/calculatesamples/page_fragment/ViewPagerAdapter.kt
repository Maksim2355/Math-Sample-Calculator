package com.example.calculatesamples.page_fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.calculatesamples.data.DataVariationsSeries
import com.example.calculatesamples.data.PointsGraphs

class ViewPagerAdapter(
   // private val data: Pair<DataVariationsSeries, PointsGraphs>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 1) {
            VariationSeriesFragment()
           // VariationSeriesFragment.newInstance(data.first)
        } else {
            GraphFragment()
            //GraphFragment.newInstance(data.second)
        }
    }

}