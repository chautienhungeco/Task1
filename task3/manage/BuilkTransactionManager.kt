package com.apero.task3.manage

import kotlinx.coroutines.*
import com.apero.task3.data.TransactionResult
import com.apero.task3.process.TransactionProcessor
import com.apero.task3.service.TransactionApiService

class BuilkTransactionManager(
    private val apiService: TransactionApiService,
    private val processor: TransactionProcessor
){

    // xử lý giao dịch hàng loạt
    suspend fun processAllTransactions(scope: CoroutineScope): List<List<TransactionResult>>{
        val transactions = withContext(Dispatchers.IO){
            apiService.fetchTransactions()
        }

        return coroutineScope{
            transactions.map{ transaction ->
                async(Dispatchers.Default){
                    processor.processTransaction(transaction)
                }
            }.awaitAll()
        }
    }
}