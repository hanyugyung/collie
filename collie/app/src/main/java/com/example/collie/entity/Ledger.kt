package com.example.collie.entity

import java.time.LocalDateTime

class Ledger(
    val transactionType: TransactionType,
    val category: Category,
    val amount: Long,
    val memo: String?,
    val assets: Assets,
    val transactionTime: LocalDateTime,
) {
    enum class TransactionType(val description: String) {
        INCOME("수입"), EXPENSE("지출"), TRANSFER("이체")
    }
}