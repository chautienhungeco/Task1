package com.apero.task3.service

import com.apero.task3.data.Transaction
import kotlinx.coroutines.delay
import kotlin.coroutines.*
class TransactionApiService{

    //Mô phỏng lấy dữ liệu từ API
    suspend fun fetchTransactions(): List<Transaction> {
        delay(3000)
        return listOf(
            Transaction("GD004",2500.0,"ACC001","ACC002",true),
            Transaction("GD005",150.0,"ACC003","ACC004",true),
            Transaction("GD006",-100.0,"ACC006","ACC005",true)

        )
    }
}