package com.apero.task3.ruler

import com.apero.task3.data.Transaction
import com.apero.task3.data.TransactionStatus
import com.apero.task3.data.TransactionResult

// tạo một quy tắc kiểm tra
typealias ValidationRule = (Transaction) -> Boolean

inline fun processWithRule(
    transaction: Transaction,
    rule: ValidationRule,
    errorMessage: String
): TransactionResult {
    return if (rule(transaction)) {
        TransactionResult(transaction, TransactionStatus.SUCCESS, "Giao dịch thành công.")
    } else {
        TransactionResult(transaction, TransactionStatus.FAILURE, errorMessage)
    }
}
