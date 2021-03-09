package com.example.calculatesamples.data

import java.io.Serializable

data class RowVariationSeries(
    val range: RangeInterval,
    var frequency: Int,
    var relativeFrequency: Float,
    var accumulatedFrequency: Float
): Serializable {
    data class RangeInterval(val min: Int, val max: Int): Serializable
}
