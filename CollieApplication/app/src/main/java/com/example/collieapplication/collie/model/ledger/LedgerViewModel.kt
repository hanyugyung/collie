package com.example.collieapplication.collie.model.ledger

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDateTime

class LedgerViewModel : ViewModel() {

    var state = MutableStateFlow("")

    private var _ledgerList by mutableStateOf(
        (1..1000).map {
            Ledger(
                TransactionType.INCOME
                , it * (-30..30).random().toLong()
                , "TEST"
                , LocalDateTime.now()
            )
        }
    )

    val ledgerList: List<Ledger>
        get() = _ledgerList

    class Ledger(
        val transactionType: TransactionType,
//        val categoryId: String, FIXME 속성값
        val amount: Long,
        val memo: String,
        val transactionTime: LocalDateTime
    )

    enum class TransactionType(
        desc: String
    ) {
        INCOME("수입"), SPENDING("지출"), TRANSFER("이체")
    }
}