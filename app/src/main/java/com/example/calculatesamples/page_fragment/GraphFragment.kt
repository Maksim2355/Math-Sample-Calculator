package com.example.calculatesamples.page_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculatesamples.R
import com.example.calculatesamples.data.PointsGraphs


private const val ARG_POINTS = "Points"


class GraphFragment : Fragment() {

    private var pointsGraphs: PointsGraphs? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { pointsGraphs = it.getSerializable(ARG_POINTS) as PointsGraphs }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_graph, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(pointsGraphs: PointsGraphs) =
            GraphFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_POINTS, pointsGraphs)
                }
            }
    }
}