package com.apero.task3.process

import com.apero.task3.data.Transaction

fun main(){
        val processor = TransactionProcessor()

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
}