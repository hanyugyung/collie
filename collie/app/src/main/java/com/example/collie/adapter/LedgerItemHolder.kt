package com.example.collie.adapter

import android.view.View
import android.widget.TextView
import com.example.collie.R

class LedgerItemHolder(root: View) {

    private var transactionType: TextView
    private var amount: TextView
    private var category: TextView

    init {
        transactionType = root.findViewById(R.id.transition_type)
        amount = root.findViewById(R.id.amount)
        category = root.findViewById(R.id.category)
    }

}