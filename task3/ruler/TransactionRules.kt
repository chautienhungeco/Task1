package com.apero.task3.ruler

import com.apero.task3.data.Transaction
import com.apero.task3.data.TransactionType

object TransactionRules {
    //check tiền > 0
    val amountPositiveRule: ValidationRule = {
        it.amount > 0
    }

    //check 2 tài khoản nguồn không trùng
    val differentAcountsRule: ValidationRule = { transaction ->
        if (transaction.type == TransactionType.TRANSFER) {
            transaction.sourceAccountId != transaction.destinationAccountId
        } else {
            true
        }
    }

    //check hạn mức giao dịch <50 củ
    val withdrawalLimiterRule: ValidationRule = { transaction ->
        if (transaction.type == TransactionType.WITHDRAWAL
            || transaction.type == TransactionType.TRANSFER
        ) {
            transaction.amount <= 50_000_000
        } else {
            true
        }
    }
}

