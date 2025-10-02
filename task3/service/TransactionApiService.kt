package com.apero.task3.service

import com.apero.task3.data.Acounts
import com.apero.task3.manage.AccountRepository
import kotlinx.coroutines.*

// Interface (OOP)
interface IAccountService {
    suspend fun fetchAccountData(accountId: String): Acounts?
    suspend fun fetchAllAccounts(): List<Acounts>
}

class TransactionApiService : IAccountService {

    /**
     * Coroutine: để mô phỏng tác vụ I/O
     * delay() mô phỏng độ trễ mạng
     */
    override suspend fun fetchAccountData(accountId: String): Acounts? =
        withContext(Dispatchers.IO) {
            delay(1500)
            println("...  Đã lấy dữ liệu tài khoản $accountId.")
            AccountRepository.getAccountById(accountId)
        }

    override suspend fun fetchAllAccounts(): List<Acounts> = withContext(Dispatchers.IO) {
        delay(3000)
        println("...  Đã lấy toàn bộ danh sách tài khoản.")
        AccountRepository.getAllAcounts()
    }
}
