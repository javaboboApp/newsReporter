package com.example.newsreporter.ui.newsreporter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsreporter.R
import com.example.newsreporter.models.NewReporter
import kotlinx.android.synthetic.main.item_adapter_new_reporter.view.*

class NewReporterItemAdapter(val listener: Listener) :
    RecyclerView.Adapter<NewReporterItemAdapter.ItemViewHolder>() {
    var list: List<NewReporter> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_adapter_new_reporter, parent, false)
        return ItemViewHolder(
            view
        )
    }

    fun remove(position: Int) {
        listener.remove(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface Listener {
        fun remove(newReporter: NewReporter)
        fun onClickItem(item: NewReporter)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(newReporter = list[position], listener = listener)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(newReporter: NewReporter, listener: Listener) = with(itemView) {
            title_textview.text = newReporter.title
            history_textview.text = newReporter.history
            setOnClickListener {
                listener.onClickItem(newReporter)
            }
        }
    }

}