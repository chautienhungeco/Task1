package com.apero.task3.ruler

import com.apero.task3.data.Transaction
import com.apero.task3.data.TransactionStatus
import com.apero.task3.data.TransactionResult

// tạo một quy tắc kiểm tra
typealias ValidationRule = (Transaction) -> Boolean

inline fun validateWithRule(
    transaction: Transaction,
    rule: ValidationRule,
    errorMessage: String
): TransactionResult? {
    return if (rule(transaction)) {
        null
    } else {
        TransactionResult(transaction, TransactionStatus.FAILURE, errorMessage)
    }
}
