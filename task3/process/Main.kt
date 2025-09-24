package com.apero.task3.process

import kotlinx.coroutines.*
import com.apero.task3.data.Transaction
import com.apero.task3.manage.BuilkTransactionManager
import com.apero.task3.process.TransactionProcessor
import com.apero.task3.service.TransactionApiService

fun main() = runBlocking {
    val apiService = TransactionApiService()
    val transactionProcessor = TransactionProcessor()
    val builkManager = BuilkTransactionManager(apiService, transactionProcessor)

    val singleTransaction = Transaction("GD007",999.0, "ACC999","ACC888",true)
    println("--- xử lý giao dịch nhỏ lẻ ---")
    val singleResult = transactionProcessor.processTransaction(singleTransaction)
    singleResult.forEach{
        println(" - ${it.message}")
    }

    println("--- đang xử lý... ... ... ... ... ...")
    println("Giả tưởng 3 giây để lấy dữ liệu ... ... ...")

    val result = builkManager.processAllTransactions(this)
    println("--- hoàn tất xử lý ---")

    result.forEach { tranSactionResult ->
        val transaction = tranSactionResult.firstOrNull()?.transaction
        println("--- Kết quả giao dịch là: ${transaction?.id} ---")
        tranSactionResult.forEach{
            result ->
            println("- ${result.message}")
        }

    }
/**        val processor = TransactionProcessor()

        // Giao dịch hợp lệ
        val validTransaction = Transaction("TXN001", 500.0, "ACC123", "ACC456", false)
        val validResults = processor.processTransaction(validTransaction)
        println("Kết quả giao dịch hợp lệ:")
        validResults.forEach { println(" - ${it.message}") }

        // Giao dịch không hợp lệ (số tiền âm)
        val invalidTransaction1 = Transaction("TXN002", -100.0, "ACC789", "ACC789", false)
        val invalidResults1 = processor.processTransaction(invalidTransaction1)
        println("\nKết quả giao dịch không hợp lệ (số tiền âm):")
        invalidResults1.forEach { println(" - ${it.message}") }

        // Giao dịch không hợp lệ (giao dịch nước ngoài vượt hạn mức)
        val invalidTransaction2 = Transaction("TXN003", 1500000.0, "ACC333", "ACC444", true)
        val invalidResults2 = processor.processTransaction(invalidTransaction2)
        println("\nKết quả giao dịch không hợp lệ (giao dịch nước ngoài):")
        invalidResults2.forEach { println(" - ${it.message}") }
*/
}