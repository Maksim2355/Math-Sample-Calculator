package com.example.calculatesamples.data

import java.io.Serializable

data class PointsGraphs(
    val graphHist: List<Point>,
    val graphAccumulatedFrequency: List<Point>
): Serializable {
    data class Point(val x: Int, val y: Int)
}