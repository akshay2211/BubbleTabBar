package com.fxn.bubbletabbarapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.fxn.bubbletabbarapp.databinding.MenuLayoutRowBinding

/**
 * @author : Akshay Sharma
 * @since : 11/01/21, Mon
 * akshay2211.github.io
 **/
class MenuRecyclerViewAdapter(var call: (pos: Int) -> Unit) :
    RecyclerView.Adapter<MenuRecyclerViewAdapter.ViewHolder>() {
    var list = arrayListOf("ViewPager Sample", "ViewPager2 Sample", "NavController Sample")

    class ViewHolder(menuLayoutRowBinding: MenuLayoutRowBinding) :
        RecyclerView.ViewHolder(menuLayoutRowBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            MenuLayoutRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.itemView as AppCompatTextView).apply {
            text = list[position]
            setOnClickListener {
                call(position)
            }
        }
    }

    override fun getItemCount() = list.size
}