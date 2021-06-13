package com.example.calculatesamples.logic

import com.example.calculatesamples.data.DataVariationsSeries
import com.example.calculatesamples.data.RowVariationSeries
import kotlin.math.pow

fun List<Float>.calculate(
    startInterval: Int
): DataVariationsSeries {
    val countInterval = minOrNull()?.let { maxOrNull()?.minus(it) }?.toInt() ?: 0
    var a = startInterval
    var b = startInterval + 1

    //Накопленная частость
    var accumFrequency = 0f
    //Среднее выборочное
    var xAverage = 0f

    val listVariationsSeries: List<RowVariationSeries> = List(countInterval) {
        var frequency = 0
        //Для float при расчетах может быть погрешность, поэтому ставим погрешность 0.1
        this.forEach { elem -> if (elem >= (a + 0.1) && elem <= (a + 0.1)) frequency++ }
        val relativeFrequency: Float = frequency.toFloat() / size.toFloat()
        accumFrequency += relativeFrequency

        val xi = a
        xAverage += relativeFrequency * xi
        //Меняем интервалы
        a = b
        b++
        RowVariationSeries(
            RowVariationSeries.RangeInterval(a, b),
            frequency,
            relativeFrequency,
            accumFrequency
        )
    }

    var dispersion = 0f
    // Найдем дисперсию, медиану и моду
    listVariationsSeries.forEachIndexed { _, rowVariationSeries ->
        val midpointInterval: Float = rowVariationSeries.range.min.toFloat()
        dispersion += (midpointInterval - xAverage).pow(2) * rowVariationSeries.relativeFrequency
    }
    val fashion = listVariationsSeries.maxByOrNull { it.frequency }?.range?.min?.toFloat() ?: 0f
    val median = if (countInterval % 2 == 0) {
        listVariationsSeries[(countInterval / 2) + 1].range.min.toFloat()
    } else {
        (listVariationsSeries[(countInterval / 2) + 1].range.min.toFloat() +
                listVariationsSeries[(countInterval / 2)].range.min.toFloat()) / 2
    }
    return DataVariationsSeries(
        xAverage,
        dispersion,
        median,
        fashion,
        listVariationsSeries
    )
}
