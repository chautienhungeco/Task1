package com.apero.task3.utility

import com.apero.task3.data.Transaction
import com.apero.task3.data.TransactionType
fun String?.toIntOrZero(): Int = this?.toIntOrNull() ?: 0

fun String?.toDoubleOrZero(): Double = this?.toDoubleOrNull() ?: 0.0

fun String?.toTransactionTypeOrNull(): TransactionType?{
    return try {
        this?.uppercase()?.let {
            type -> TransactionType.valueOf(type)
        }
    }catch (e: IllegalAccessException){
        null
    }
}