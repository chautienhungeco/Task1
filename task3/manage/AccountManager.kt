package com.apero.task3.manage

import com.apero.task3.data.Acounts
import com.apero.task3.manage.AccountRepository
import java.util.*

class AccountManager {
    private val repository = AccountRepository

    //thêm tài khoản
    fun createAccount(ownerName: String, initialBalance: Double): String {
        val newId = "ACC" + (repository.getAllAcounts().size + 1).toString().padStart(3, '0')
        val newAccount = Acounts(
            id = newId,
            ownerName = ownerName,
            balence = initialBalance,
            isVerified = false,
            creationDate = System.currentTimeMillis()
        )

        return if (repository.addAccount(newAccount)) {
            "Tạo tài khoản thành công. ID: $newId"
        } else {
            "Lỗi: ID ($newId) đã tồn tại trong hệ thống."
        }
    }

    // Sửa Tên Chủ sở hữu
    fun modifyAccount(accountId: String, newOwnerName: String): String {
        return if (repository.updateAccountOwner(accountId, newOwnerName)) {
            "Cập nhật tài khoản $accountId thành công. Tên mới: $newOwnerName"
        } else {
            "Lỗi: Không tìm thấy tài khoản với ID $accountId để cập nhật."
        }
    }

    // Xóa Tài khoản
    fun removeAccount(accountId: String): String {
        val account = repository.getAccountById(accountId)

        return if (account != null) {
            if (account.balence == 0.0) {
                if (repository.deleteAccount(accountId)) {
                    "Xóa tài khoản $accountId thành công."
                } else {
                    "Lỗi hệ thống: Xóa tài khoản $accountId thất bại."
                }
            } else {
                "Xóa thất bại: Tài khoản $accountId còn số dư (${account.balence})."
            }
        } else {
            "Lỗi: Không tìm thấy tài khoản với ID $accountId để xóa."
        }
    }
}