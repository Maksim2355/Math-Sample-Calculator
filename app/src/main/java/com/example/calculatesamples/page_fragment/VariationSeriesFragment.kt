package com.example.calculatesamples.page_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculatesamples.R
import com.example.calculatesamples.data.DataVariationsSeries

private const val ARGS_DATA_VARIATION_SERIES = "VARIATION_SERIES"

class VariationSeriesFragment : Fragment() {

    private var paramVariationSeriesFragment: DataVariationsSeries? = null

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
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_variation_series, container, false)
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