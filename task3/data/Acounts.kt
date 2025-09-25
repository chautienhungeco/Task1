package com.apero.task3.data

enum class TransactionType {
    DEPOSIT,
    WITHDRAWAL,
    TRANSFER
}

data class Acounts(
    val id: String,
    val ownerName: String,
    val currency: String = "VND",
    val creationDate: Long = System.currentTimeMillis(),
    var balence: Double = 0.0,
    var isVerified: Boolean = false
)
