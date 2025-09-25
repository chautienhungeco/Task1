package com.apero.task3.process

import com.apero.task3.data.*
import com.apero.task3.manage.AccountRepository
import com.apero.task3.manage.findByMinBalance
import com.apero.task3.service.TransactionApiService
import com.apero.task3.utility.toDoubleOrZero
import com.apero.task3.utility.toTransactionTypeOrNull
import kotlinx.coroutines.*
import java.util.*

val apiService = TransactionApiService()
val transactionProcessor = TransactionProcessor()

class MenuHandler(val scope: CoroutineScope) {

    fun showMenu() {
        println("===QUẢN LÝ HỆ THỐNG GIAO DỊCH===")
        println("---------------------------------")
        println("1. Hiển thị danh sách các tài khoản!")
        println("2. Lọc tài khoản theo mức số dư")
        println("3. Thực hiện giao dịch đơn lẻ")
        println("4. Xử lý giao dịch hàng loại (đã nạp data)")
        println("5. Lấy dữ liệu tải khoản")
        println("0. Thoát chương trình ==========")

        print("Mời nhập lựa chọn: ")
    }

    suspend fun runMenu() {
        var choice: Int
        do {
            showMenu()
            choice = readln().toDoubleOrZero().toInt()

            when (choice) {
                1 -> displayAllAccounts()
                2 -> filterAccounts()
                3 -> handleSingleTransaction()
            }
        } while (choice != 0)
    }

    fun displayAllAccounts() {
        println("\n --- Danh sách tài khoản hiện có ---")
        val accounts = AccountRepository.getAllAcounts()
        for (account in accounts) {
            println(
                "${account.id}, Chủ tk: ${account.ownerName}, Số dư:${account.balence}  ${account.currency}," +
                        " Trạng thái: ${if (account.isVerified) "Đã xác thực" else "Chưa xác thực"}"
            )
        }
    }

    fun filterAccounts() {
        println("\n--- Lọc Tài khoản theo số dư tối thiểu---")
        print("Nhập số dư tối thiếu: ")
        val minBalance = readLine().toDoubleOrZero()
        val filteredAccounts = AccountRepository.getAllAcounts().findByMinBalance(minBalance)

        if (filteredAccounts.isEmpty()) {
            println("KHông có tài khoản nào có số dư lớn hơn $minBalance")
            return
        } else {
            println("\nKết quả tìm kiếm: ")
            filteredAccounts.forEach {
                println("ID: ${it.id}, Số dư: ${it.balence} ${it.currency}")
            }
        }
    }

    fun handleSingleTransaction() {
        println("\n---Thực hiện giao dịch đơn lẻ--")
        print("Điền đúng 1 trong 3 loại giao dịch (DEPOSIT/WITHDRAWAL/TRANSFER): ")
        val type = readLine().toTransactionTypeOrNull()?.run {
            println("Loại dịch vụ không đúng!")
            return
        }
        print("Số tiền: ")
        val amount = readLine().toDoubleOrZero()

        print("Tài khoản nguồn ID (VD: ACC001): ")
        val src = readLine() ?: ""

        val dest = if (type == TransactionType.TRANSFER) {
            print("Tài khoàn đích ID (VD: ACC002): ")
            readLine()
        } else {
            null
        }

        val transaction = type?.let {
            Transaction(
                "TX-${System.nanoTime()}",
                it,
                amount,
                src,
                dest
            )
        }
        val result = transaction?.let { transactionProcessor.processTransaction(it) }

        println("--- KÊT QUẢ ---")
        val hasErrors = result?.any { it.status == TransactionStatus.FAILURE }
        if (hasErrors == true) {
            println("Giao dịch THẤT BẠI, LỖI: ")
            result.forEach { result ->
                if (result.status == TransactionStatus.FAILURE) {
                    println("X ${result.message}")
                } else {
                    //không làm gì
                }
            }
        } else {
            println("Giao dịch thành công!")
            println("Thông báo biến đôộng số dư ${src}: ${AccountRepository
                .getAccountBalance(src) ?: "Không tìm thấy"}")
        }
    }

    suspend fun handleAsyncFetch(){
        println("\n---5. Lấy dữ liệu bất đồng bộ---")
        print("Nhập ID tài khoản cần lấy data (VD: ACC001): ")
        val accountId = readLine() ?: return

        val startTime = System.currentTimeMillis()
        val account = apiService.fetchAccountData(accountId) // Gọi Suspend Fun
        val endTime = System.currentTimeMillis()

        println("\n--- KẾT QUẢ ---")
        println("Thời gian chờ: ${endTime - startTime} ms")
        if (account != null) {
            println("   Đã lấy dữ liệu thành công cho ${account.ownerName}")
        } else {
            println("   Không tìm thấy tài khoản $accountId")
        }
    }
}