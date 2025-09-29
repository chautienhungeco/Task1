package com.apero.task3.process

import com.apero.task3.data.*
import com.apero.task3.manage.AccountManager
import com.apero.task3.manage.AccountRepository
import com.apero.task3.manage.findByMinBalance
import com.apero.task3.service.TransactionApiService
import com.apero.task3.utility.toDoubleOrZero
import com.apero.task3.utility.toTransactionTypeOrNull
import kotlinx.coroutines.*
import java.util.*

val apiService = TransactionApiService()
val transactionProcessor = TransactionProcessor()
val accountManager = AccountManager()

class MenuHandler(val scope: CoroutineScope) {

    fun showMenu() {
        println("===QUẢN LÝ HỆ THỐNG GIAO DỊCH===")
        println("---------------------------------")
        println("1. Hiển thị danh sách các tài khoản!")
        println("2. Lọc tài khoản theo mức số dư")
        println("3. Thực hiện giao dịch đơn lẻ")
        println("4. Xử lý giao dịch hàng loại (đã nạp data)")
        println("5. Lấy dữ liệu tải khoản")
        println("6. Quản lý tải khoản(Thêm, Sửa, Xóa)")
        println("0. Thoát chương trình ==========")

        print("Mời nhập lựa chọn: ")
    }

    suspend fun runMenu() {
        var choice: Int
        do {
            showMenu()
            choice = readLine().toDoubleOrZero().toInt()

            when (choice) {
                1 -> displayAllAccounts()
                2 -> filterAccounts()
                3 -> handleSingleTransaction()
                4 -> handleBulkTransaction()
                5 -> handleAsyncFetch()
                6 -> handleAccountManagement()
                0 -> println("Cảm ơn đã sử dụng, hẹn gặp lại!")
                else -> println("Lựa chọn không hợp lệ, thử lại")
            }
        } while (choice != 0)
    }

    fun displayAllAccounts() {
        println("\n --- Danh sách tài khoản hiện có ---")
        val accounts = AccountRepository.getAllAcounts()
        for (account in accounts) {
            println(
                "ID: ${account.id}, Chủ tk: ${account.ownerName}, Số dư:${account.balence}  ${account.currency}," +
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
            //chưa xử lý
        }
        println("\nKết quả tìm kiếm: ")
        filteredAccounts.forEach {
            println("ID: ${it.id}, Số dư: ${it.balence} ${it.currency}")
        }
    }

    fun handleSingleTransaction() {
        println("\n---Thực hiện giao dịch đơn lẻ--")
        print("Điền đúng 1 trong 3 loại giao dịch (DEPOSIT/WITHDRAWAL/TRANSFER): ")
        val type = readLine().toTransactionTypeOrNull() ?: run {
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

        val transaction = Transaction(UUID.randomUUID().toString(), type, amount, src, dest)
        val result = transactionProcessor.processTransaction(transaction)

        println("--- KÊT QUẢ ---")
        val hasErrors = result.any { it.status == TransactionStatus.FAILURE }
        if (hasErrors) {
            println("Giao dịch THẤT BẠI, LỖI: ")
            result.forEach { result ->
                if (result.status == TransactionStatus.FAILURE) {
                    println(" X ${result.message}")
                } else {
                    //TODO
                }
            }
        } else {
            println("Giao dịch thành công!")
            println(
                "Thông báo biến đôộng số dư ${src}: ${
                    AccountRepository
                        .getAccountBalance(src) ?: "Không tìm thấy"
                }"
            )
        }
    }

    suspend fun handleBulkTransaction() {
        println("\n 4. Xử lý giao dịch hàng loạt (giả định)---")
        println("Mô phỏng 3 giao dịch phức tạp (2s chờ) ")

        val sampleTransactions = listOf(
            Transaction("ACC01", TransactionType.WITHDRAWAL, 1_000_000.0, "ACC01"),
            Transaction("AC002", TransactionType.TRANSFER, 60_000_000.0, "ACC08"),
            Transaction("AC003", TransactionType.DEPOSIT, 500_000.0, "ACC01"),
            Transaction(
                "GD006",
                TransactionType.WITHDRAWAL,
                100.0,
                "ACC_NON_EXIT",
                description = "Tài khoản không tồn tại"
            ),
        )

        val startTime = System.currentTimeMillis()
        val bulkResults = transactionProcessor.bulkProcess(sampleTransactions)
        val endTime = System.currentTimeMillis()

        println("\n --- Xử lý hoàn thành trong ${endTime - startTime} ms")
        bulkResults.forEach { transactionResults ->
            val transaction = transactionResults.first().transaction
            println("--- Giao dịch ${transaction.id} (${transaction.type})")

            if (transactionResults.any { it.status == TransactionStatus.FAILURE }) {
                transactionResults.filter { it.status == TransactionStatus.FAILURE }
                    .forEach { println("X Lỗi : ${it.message}") }
            } else {
                println("Thành công")
            }
        }
    }

    suspend fun handleAsyncFetch() {
        println("\n---5. Lấy dữ liệu bất đồng bộ---")
        print("Nhập ID tài khoản cần lấy data (VD: ACC001): ")
        val accountId = readLine() ?: return

        val startTime = System.currentTimeMillis()
        val account = apiService.fetchAccountData(accountId) // Gọi Suspend Fun
        val endTime = System.currentTimeMillis()

        println("\n--- KẾT QUẢ ---")
        println("Thời gian chờ: ${endTime - startTime} ms")
        if (account != null) {
            println("   ĐANG... lấy dữ liệu thành công cho ${account.ownerName}")
        } else {
            println("   Không tìm thấy tài khoản $accountId")
        }
    }

    fun AccountMenu() {
        println("---6. Menu Quản Lý Tài Khoản---")
        println("6.1 Thêm tài khoản mới")
        println("6.2 Sửa tên chủ tài khoản")
        println("6.3 Xóa tài khoản")
        println("0. Quay lại")
        print("Nhập lựa chọn (1,2,3,0): ")
    }

    fun handleAccountManagement() {
        var choice: Int
        do {
            AccountMenu()
            choice = readLine().toDoubleOrZero().toInt()
            when (choice) {
                1 -> handleAddAccount()
                2 -> handleUpdateAccount()
                3 -> handleDeleteAccount()
                0 -> println("Quay lại menu chính...")
                else -> println("Lựa chọn không hợp lệ")
            }
        } while (choice != 0)
    }

    fun handleAddAccount() {
        println("---6.1 Thêm tài khoản mới---")
        print("Nhập tên chủ sở hữu: ")
        val ownerName = readLine()?.trim()
        print("Nhập số dư ban đầu: ")
        val initialBalance = readln().toDoubleOrZero()
        if (ownerName.isNullOrBlank()) {
            println("Lỗi tên chủ sở hữu không đúng")
            return
        } else {
            val result = accountManager.createAccount(ownerName, initialBalance)
            println(result)
        }
    }

    fun handleUpdateAccount() {
        println("---Sửa tên chủ sở hữu---")
        print("Nhập ID tài khoản cần sửa(vd: ACC01): ")
        val accountId = readLine()?.trim()
        print("Nhập tên chủ sở hữu mới: ")
        val newOwnerName = readLine()?.trim()

        if (accountId.isNullOrBlank() || newOwnerName.isNullOrBlank()) {
            println("Tài khoan và tên không đợc để trống")
        } else {
            val result = accountManager.modifyAccount(accountId, newOwnerName)
            println(result)
        }
    }

    fun handleDeleteAccount() {
        print("---Nhập ID tài khoản cần xóa ")
        val accountId = readLine()?.trim()

        if (accountId.isNullOrBlank()) {
            println("ID ta khoản không được để trống")
        } else {
            val result = accountManager.removeAccount(accountId)
            println(result)
        }
    }
}