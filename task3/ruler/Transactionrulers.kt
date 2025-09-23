package com.apero.task3.ruler

import com.apero.task3.ruler.ValidationRule

object TransactionRuler {

    val amountPositiveRule: ValidationRule = { it.amount > 0 }

    val differentAccountsRule: ValidationRule = { it.sourceAccount != it.destinationAccount }

    val foreignTransactionLimitRule: ValidationRule = { transaction ->
        !transaction.isForeign || (transaction.isForeign && transaction.amount <= 1_000_000)
    }
}

