package com.apero.task3.data

data class Transaction(
    val id: String,
    val type: TransactionType,
    val amount: Double,
    val sourceAccountId: String,
    val destinationAccountId: String? = null,
    val description: String? = null
)

data class TransactionResult(
    val transaction: Transaction,
    val status: TransactionStatus,
    val message: String
)