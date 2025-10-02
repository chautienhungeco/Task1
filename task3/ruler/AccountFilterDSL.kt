package com.apero.task3.ruler

import com.apero.task3.data.Acounts

typealias AccountPredicate = (Acounts) -> Boolean

class AccountFilterBuilder {
    private var criteria: AccountPredicate = { true }

    fun has(precdicate: AccountPredicate) {
        val oldCriteria = criteria
        criteria = { account -> oldCriteria(account) && precdicate(account) }
    }

    fun balanceCompare(minBalance: Double) {
        has { it.balence >= minBalance }
    }

    fun isVerify() {
        has { it.isVerified }
    }

    fun isNotVerify() {
        has { !it.isVerified }
    }

    fun nameContains(text: String) {
        val lowerCaseText = text.lowercase()
        has { it.ownerName.lowercase().contains(lowerCaseText) }
    }

    fun build(): AccountPredicate = criteria

}

fun buildFilter(block: AccountFilterBuilder.() -> Unit): AccountPredicate {
    val builder = AccountFilterBuilder()
    builder.block()
    return builder.build()
}
