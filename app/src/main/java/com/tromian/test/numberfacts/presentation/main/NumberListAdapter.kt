package com.tromian.test.numberfacts.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tromian.test.numberfacts.R
import com.tromian.test.numberfacts.model.Number

class NumberListAdapter(val itemCallback: (itemId: Int) -> Unit) :
    ListAdapter<Number, NumberListAdapter.NumberViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Number>() {
            override fun areItemsTheSame(oldItem: Number, newItem: Number): Boolean =
                oldItem.text == newItem.text

            override fun areContentsTheSame(oldItem: Number, newItem: Number): Boolean =
                oldItem == newItem
        }
    }

    inner class NumberViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvNumber: TextView = itemView.findViewById(R.id.tvNumberPreview)
        private val tvFact: TextView = itemView.findViewById(R.id.tvFactPreview)

        fun bind(number: Number) {
            tvNumber.text = number.number.toString()
            tvFact.text = number.text
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NumberViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return NumberViewHolder(inflater.inflate(R.layout.preview_history_item, parent, false))
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            itemCallback(position)
        }
    }
}