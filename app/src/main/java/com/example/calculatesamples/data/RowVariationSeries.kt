package com.example.calculatesamples.data

data class RowVariationSeries(
    val range: RangeInterval,
    val frequency: Int,
    val relativeFrequency: Float,
    val accumulatedFrequency: Float
) {
    data class RangeInterval(val min: Int, val max: Int)
}
