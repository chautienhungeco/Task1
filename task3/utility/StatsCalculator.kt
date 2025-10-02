package com.apero.task3.utility

class StatsCalculator<T : Number> {

    fun calculateSum(items: List<T>): Double {
        var sum = 0.0
        for (item in items) {
            sum += item.toDouble()
        }
        return sum
    }

    fun calculateAverage(items: List<T>): Double {
        if (items.isEmpty()) {
            return 0.0
        } else {
            val sum = calculateSum(items)
            return sum / items.size
        }
    }
}
