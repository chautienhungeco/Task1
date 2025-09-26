package com.apero.task3.process

import com.apero.task3.data.*
import com.apero.task3.manage.AccountRepository
import com.apero.task3.data.TransactionResult
import com.apero.task3.ruler.TransactionRules
import com.apero.task3.ruler.validateWithRule
import kotlinx.coroutines.*

class TransactionProcessor {

    //xử lý giao dịch đơn
    fun processTransaction(transaction: Transaction): List<TransactionResult> {
        val validationErrors = mutableListOf<TransactionResult>()
        val sourceAccount = AccountRepository.getAccountById(transaction.sourceAccountId)

        validateWithRule(
            transaction,
            TransactionRules.amountPositiveRule,
            "Số tiền cần nhiều hơn 0!"
        )?.let {
            validationErrors.add(it)
        }
        validateWithRule(
            transaction,
            TransactionRules.differentAcountsRule,
            "Tài khoản nhận cần khác tài khoản chuyển!"
        )?.let {
            validationErrors.add(it)
        }
        validateWithRule(
            transaction,
            TransactionRules.withdrawalLimiterRule,
            "Giới hạn chuyển tiền là 50 củ"
        )?.let {
            validationErrors.add(it)
        }

        if (sourceAccount == null) {
            validationErrors.add(
                TransactionResult(
                    transaction,
                    TransactionStatus.FAILURE,
                    "Tài khoản nguồn không đúng!"
                )
            )
            return validationErrors
        } else {
            //
        }
        if (transaction.amount > sourceAccount.balence) {
            validationErrors.add(
                TransactionResult(
                    transaction,
                    TransactionStatus.FAILURE,
                    "Số dư không đủ để tực hiện giao dịch"
                )
            )
        }

        return if (validationErrors.isEmpty()) {
            val success = performTransaction(transaction)
            if (success) {
                listOf(
                    TransactionResult(
                        transaction,
                        TransactionStatus.SUCCESS,
                        "Giao dịch thành công!"
                    )
                )
            } else {
                listOf(TransactionResult(transaction, TransactionStatus.FAILURE, "Lỗi hệ thống??"))
            }
        } else {
            validationErrors
        }
    }


    fun performTransaction(transaction: Transaction): Boolean {
        return when (transaction.type) {
            TransactionType.DEPOSIT -> AccountRepository.updateBalance(
                transaction.sourceAccountId,
                transaction.amount
            )

            TransactionType.WITHDRAWAL -> AccountRepository.updateBalance(
                transaction.sourceAccountId,
                -transaction.amount
            )

            TransactionType.TRANSFER -> {
                val destId = transaction.destinationAccountId ?: return false
                AccountRepository.updateBalance(transaction.sourceAccountId, -transaction.amount)
                        && AccountRepository.updateBalance(destId, transaction.amount)
            }
        }

    }

    //xử lý loạt giao dịch với coroutines
    suspend fun bulkProcess(transaction: List<Transaction>): List<List<TransactionResult>> = coroutineScope {
        transaction.map { transaction ->
            async(Dispatchers.Default) {
                processTransaction(transaction)
            }
        }.awaitAll()
    }
}