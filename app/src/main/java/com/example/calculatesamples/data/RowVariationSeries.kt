package com.example.calculatesamples.data

data class RowVariationSeries(
    val range: RangeInterval,
    var frequency: Int,
    var relativeFrequency: Float,
    var accumulatedFrequency: Float
) {
    data class RangeInterval(val min: Int, val max: Int)
}
