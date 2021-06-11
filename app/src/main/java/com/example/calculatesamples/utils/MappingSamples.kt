package com.example.calculatesamples.utils

fun mapToSample(s: String): List<Float> {
    val sampleStr = s.trim()
    return if (sampleStr.isNotEmpty()) {
        sampleStr.split(' ').map { it.toFloat() }
    } else emptyList()
}
