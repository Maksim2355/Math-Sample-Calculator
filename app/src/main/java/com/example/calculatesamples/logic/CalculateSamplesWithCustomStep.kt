package com.example.calculatesamples.logic

import com.example.calculatesamples.data.DataVariationsSeries
import com.example.calculatesamples.data.RowVariationSeries
import kotlin.math.pow

fun List<Float>.calculateWithCustomStep(
    startInterval: Int,
    stepInterval: Int,
): DataVariationsSeries {
    val sizeInterval = ((maxOrNull() ?: startInterval.toFloat()) - startInterval)
    val countInterval = (sizeInterval / stepInterval.toFloat()) + (sizeInterval % stepInterval) + 1
    var a = startInterval
    var b = startInterval + stepInterval
    //Накопленная частость
    var accumFrequency = 0f
    //Среднее выборочное
    var xAverage = 0f
    val listVariationsSeries: List<RowVariationSeries> = List(countInterval.toInt()) {
        val rangeInterval = RowVariationSeries.RangeInterval(a, b)

        var frequency = 0
        forEach { if (it >= rangeInterval.min && it < rangeInterval.max) frequency++ }
        val relativeFrequency: Float = frequency.toFloat() / size
        accumFrequency += relativeFrequency

        val xi: Float = (rangeInterval.max + rangeInterval.min).toFloat() / 2
        xAverage += relativeFrequency * xi
        //Меняем интервалы
        a = b
        b += stepInterval
        RowVariationSeries(rangeInterval, frequency, relativeFrequency, accumFrequency)
    }

    var dispersion = 0f
    // Найдем дисперсию, медиану и моду
    listVariationsSeries.forEach { rowVariationSeries ->
        val midpointInterval: Float =
            (rowVariationSeries.range.max + rowVariationSeries.range.min - 1).toFloat() / 2
        dispersion += (midpointInterval - xAverage).pow(2) * rowVariationSeries.relativeFrequency
    }

    val maxFrequency = listVariationsSeries.maxByOrNull { it.frequency }
    val indexWithFrequencyGreater: Int = listVariationsSeries.indexOfFirst {
        it.accumulatedFrequency > 0.5
    }
    val maxIntervalFrequency = listVariationsSeries.indexOfFirst { it == maxFrequency }
    val fashion = listVariationsSeries[maxIntervalFrequency].range.min +
            stepInterval.toFloat() *
            ((listVariationsSeries[maxIntervalFrequency].frequency - listVariationsSeries[maxIntervalFrequency - 1].frequency)
                    / ((listVariationsSeries[maxIntervalFrequency].frequency - listVariationsSeries[maxIntervalFrequency - 1].frequency)
                    - (listVariationsSeries[maxIntervalFrequency].frequency - listVariationsSeries[maxIntervalFrequency + 1].frequency)))
                .toFloat()
    val median =
        listVariationsSeries[indexWithFrequencyGreater].range.min +
                stepInterval.toFloat() *
                ((0.5 * size -
                        (listVariationsSeries[indexWithFrequencyGreater - 1].accumulatedFrequency * size))
                        / listVariationsSeries[indexWithFrequencyGreater].frequency).toFloat()

    return DataVariationsSeries(
        xAverage,
        dispersion,
        median,
        fashion,
        listVariationsSeries
    )
}
