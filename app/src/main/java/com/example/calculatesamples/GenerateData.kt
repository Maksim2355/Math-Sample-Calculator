import com.example.calculatesamples.data.DataVariationsSeries
import com.example.calculatesamples.data.PointsGraphs
import com.example.calculatesamples.data.RowVariationSeries
import kotlin.math.pow


class GenerateData(
    startInterval: Int,
    stepInterval: Int,
    samples: List<Int>
) {

    private val sizeSamples: Int = samples.size
    private val maxSamplesElement: Int = samples.maxOrNull() ?: startInterval

    val variationsSeries: DataVariationsSeries

    init {
        val sizeInterval = maxSamplesElement - startInterval
        val countInterval = (sizeInterval / stepInterval) + (sizeInterval % stepInterval) + 1
        var a = startInterval
        var b = startInterval + stepInterval
        //Накопленная частость
        var accumFrequency = 0f
        //Среднее выборочное
        var xAverage = 0f
        val listVariationsSeries: List<RowVariationSeries> = List(countInterval) { _ ->
            val rangeInterval = RowVariationSeries.RangeInterval(a, b)

            var frequency = 0
            samples.forEach { elem ->
                if (elem >= rangeInterval.min && elem < rangeInterval.max) frequency++
            }
            val relativeFrequency: Float = frequency.toFloat() / sizeSamples.toFloat()
            accumFrequency += relativeFrequency

            xAverage += relativeFrequency * ((rangeInterval.max + rangeInterval.min).toFloat() / 2)
            //Меняем интервалы
            a = b
            b += stepInterval
            RowVariationSeries(rangeInterval, frequency, relativeFrequency, accumFrequency)
        }

        var dispersion = 0f
        var median = 0f
        var fashion = 0f
        // Найдем дисперсию, медиану и моду
        listVariationsSeries.forEachIndexed { idx, rowVariationSeries ->
            val midpointInterval: Float =
                if (stepInterval != 1) {
                    (rowVariationSeries.range.max + rowVariationSeries.range.min - 1).toFloat() / 2
                } else {
                    rowVariationSeries.range.min.toFloat()
                }
            dispersion += (midpointInterval - xAverage).pow(2) * rowVariationSeries.relativeFrequency
        }

        val isIntervalSeries = stepInterval > 1
        val maxFrequency = listVariationsSeries.maxByOrNull { it.frequency }
        if (isIntervalSeries) {
            val indexWithFrequencyGreater: Int = listVariationsSeries.indexOfFirst {
                it.accumulatedFrequency > 0.5
            }
            val maxIntervalFrequency = listVariationsSeries.indexOfFirst { it == maxFrequency }
            fashion = listVariationsSeries[maxIntervalFrequency].range.min +
                    stepInterval.toFloat() *
                    ((listVariationsSeries[maxIntervalFrequency].frequency - listVariationsSeries[maxIntervalFrequency - 1].frequency)
                            / ((listVariationsSeries[maxIntervalFrequency].frequency - listVariationsSeries[maxIntervalFrequency - 1].frequency)
                            - (listVariationsSeries[maxIntervalFrequency].frequency - listVariationsSeries[maxIntervalFrequency + 1].frequency))
                            ).toFloat()
            median =
                listVariationsSeries[indexWithFrequencyGreater].range.min +
                        stepInterval.toFloat() *
                        ((0.5 * sizeSamples -
                                (listVariationsSeries[indexWithFrequencyGreater - 1].accumulatedFrequency * sizeSamples))
                                / listVariationsSeries[indexWithFrequencyGreater].frequency).toFloat()
        } else {
            fashion = maxFrequency?.range?.min?.toFloat() ?: 0f
            median = if (countInterval % 2 == 0) {
                listVariationsSeries[(countInterval / 2) + 1].range.min.toFloat()
            } else {
                (listVariationsSeries[(countInterval / 2) + 1]
                    .range.min.toFloat() + listVariationsSeries[(countInterval / 2)].range.min.toFloat()) / 2
            }
        }
        variationsSeries = DataVariationsSeries(
            xAverage,
            dispersion,
            median,
            fashion,
            listVariationsSeries
        )
    }

    fun createDataGraphs(): PointsGraphs {
        return PointsGraphs(emptyList(), emptyList())
    }
}