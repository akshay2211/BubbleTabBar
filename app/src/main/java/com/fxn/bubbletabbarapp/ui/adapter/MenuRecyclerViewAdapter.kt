package com.fxn.bubbletabbarapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.fxn.bubbletabbarapp.R

/**
 * @author : Akshay Sharma
 * @since : 11/01/21, Mon
 * akshay2211.github.io
 **/
class MenuRecyclerViewAdapter(var call: (pos: Int) -> Unit) :
    RecyclerView.Adapter<MenuRecyclerViewAdapter.ViewHolder>() {
    var list = arrayListOf("ViewPager Sample", "ViewPager2 Sample", "NavController Sample")

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.menu_layout_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.itemView as AppCompatTextView).apply {
            text = list[position]
            setOnClickListener {
                call(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}