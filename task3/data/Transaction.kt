package com.apero.task3.data

data class Transaction(
    val id: String,
    val amount: Double
)

package com.example.financialtransactionapp.data

data class Transaction(
    val id: String,
    val amount: Double,
    val sourceAccount: String,
    val destinationAccount: String,
    val isForeign: Boolean
)

data class TransactionResult(
    val transaction: Transaction,
    val status: TransactionStatus,
    val message: String
)