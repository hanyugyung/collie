package com.example.collie

import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.collie.adapter.LedgerAdapter
import com.example.collie.entity.Assets
import com.example.collie.entity.Category
import com.example.collie.entity.Ledger
import java.time.LocalDateTime

class LedgerListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ledger_list)
        setTitle("2024.03")

        val category = Category(1L, "식비")
        val category2 = Category(2L, "급여")
        val assets = Assets(1L, "급여 통장", 1000000, "전재산")

        val ledgerList = arrayListOf(
            Ledger(Ledger.TransactionType.EXPENSE, category, 10000L, "점심밥", assets, LocalDateTime.now())
            , Ledger(Ledger.TransactionType.INCOME, category2, 3000L, "급여", assets, LocalDateTime.now())
        )

        findViewById<ListView>(R.id.listView1).apply {
            this.adapter = LedgerAdapter(applicationContext, R.layout.ledger_item, ledgerList)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}