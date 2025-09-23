package com.apero.task3.process

import com.apero.task3.data.Transaction
import com.apero.task3.data.TransactionResult
import com.apero.task3.ruler.TransactionRuler
import com.apero.task3.ruler.processWithRule

class TransactionProcessor {
    fun processTransaction(transaction: Transaction): List<TransactionResult> {
        val results = mutableListOf<TransactionResult>()

        // Áp dụng các quy tắc bằng cách truyền lambda vào Higher-Order Function
        results.add(processWithRule(transaction, TransactionRuler.amountPositiveRule, "Số tiền cần lớn hơn 0."))
        results.add(processWithRule(transaction, TransactionRuler.differentAccountsRule, "Tài khoản nhận cần khác tài khoản gửi!"))
        results.add(processWithRule(transaction, TransactionRuler.foreignTransactionLimitRule, "Số tiền nước ngoài chuyển duưới 1_000_000."))

        return results
    }
}