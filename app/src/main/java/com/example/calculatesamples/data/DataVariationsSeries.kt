package com.example.calculatesamples.data

import java.io.Serializable

data class DataVariationsSeries(
    val sampleAverage: Float,
    val variance: Float,
    val median: Float,
    val fashion: Float,
    val variationSeries: List<RowVariationSeries>
) : Serializable