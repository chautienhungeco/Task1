package com.apero.task3.manage

import com.apero.task3.data.Acounts
import com.apero.task3.data.Transaction

object AccountRepository{
    val accounts = mutableMapOf<String, Acounts>()
    val transactions = mutableListOf<Transaction>()

    init {
        val acc1 = Acounts("ACC01","Hưng hay ho", balence = 10_000_000.0, isVerified = true)
        val acc2 = Acounts("ACC02","Lê Văn Luyện", balence = 999_999_999.0)
        val acc3 = Acounts("ACC03","Trịnh Trần Phương Tuấn", balence = 5_000_000.0, currency = "USD")

        accounts[acc1.id] = acc1
        accounts[acc2.id] = acc2
        accounts[acc3.id] = acc3
    }

    fun getAllAcounts(): List<Acounts> = accounts.values.toList()

    fun getAccountById(id: String): Acounts? = accounts[id]

    fun saveTransaction(transaction: Transaction) {
        transactions.add(transaction)
    }

    fun getAccountBalance(accountId: String): Double? {
        return getAccountById(accountId)?.balence
    }

    fun updateBalance(accountId: String, amount: Double): Boolean{
        val account = getAccountById(accountId)
        return if (account != null){
            account.balence += amount
            true
        }else{
            false
        }
    }
}

fun List<Acounts>.findByMinBalance(minBalance: Double): List<Acounts>{
    return this.filter {
        it.balence >= minBalance
    }
}
