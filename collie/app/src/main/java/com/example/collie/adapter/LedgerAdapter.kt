package com.example.collie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.collie.R
import com.example.collie.entity.Ledger

class LedgerAdapter(
    private val context: Context
    , private val layout: Int
    , private val dateList: MutableList<Ledger>
): BaseAdapter() {

    override fun getCount(): Int {
        return dateList.size
    }

    override fun getItem(position: Int): Any {
        return dateList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        if (convertView == null) { // 아직 뷰 객체가 준비 돼 있지 않은 상태
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(layout, null)

            val holder = LedgerItemHolder(view)
            view.tag = holder

            return view
        } else {
            convertView.findViewById<TextView>(R.id.transition_type).text = dateList[position].transactionType.toString()
            convertView.findViewById<TextView>(R.id.amount).text = dateList[position].amount.toString()
            convertView.findViewById<TextView>(R.id.category).text = dateList[position].category.toString()

        }

        return convertView
    }
}