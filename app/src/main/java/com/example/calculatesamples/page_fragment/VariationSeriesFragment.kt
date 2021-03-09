package com.example.calculatesamples.page_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calculatesamples.R
import com.example.calculatesamples.adapter.VariationsSeriesAdapter
import com.example.calculatesamples.data.DataVariationsSeries
import com.example.calculatesamples.databinding.FragmentVariationSeriesBinding

private const val ARGS_DATA_VARIATION_SERIES = "VARIATION_SERIES"

class VariationSeriesFragment : Fragment() {

    private var paramVariationSeriesFragment: DataVariationsSeries? = null

    private lateinit var binding: FragmentVariationSeriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramVariationSeriesFragment = it.getSerializable(
                ARGS_DATA_VARIATION_SERIES
            ) as DataVariationsSeries
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentVariationSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            sampleAverageTv.text =
                getString(R.string.sample_average, paramVariationSeriesFragment?.sampleAverage)
            varianceTv.text = getString(R.string.variance, paramVariationSeriesFragment?.variance)
            medianTv.text = getString(R.string.median, paramVariationSeriesFragment?.median)
            fashionTv.text = getString(R.string.fashion, paramVariationSeriesFragment?.fashion)
            variationSeriesRv.layoutManager = LinearLayoutManager(requireContext())
            variationSeriesRv.adapter = VariationsSeriesAdapter(
                paramVariationSeriesFragment?.variationSeries ?: emptyList()
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(dataVariationsSeries: DataVariationsSeries) =
            VariationSeriesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARGS_DATA_VARIATION_SERIES, dataVariationsSeries)
                }
            }
    }
}