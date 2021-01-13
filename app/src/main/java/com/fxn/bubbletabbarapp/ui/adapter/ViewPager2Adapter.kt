package com.fxn.bubbletabbarapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fxn.bubbletabbarapp.R
import kotlinx.android.synthetic.main.fragment_child.view.*

/**
 * @author : Akshay Sharma
 * @since : 11/01/21, Mon
 * akshay2211.github.io
 **/
/**
 * [ViewPager2Adapter] adapter for ViewPager2 Example
 */
class ViewPager2Adapter : RecyclerView.Adapter<ViewPager2Adapter.ViewHolder>() {
    var list = arrayListOf("Home", "Logger", "Documents", "Settings")

    /**
     * [ViewHolder] internal custom holder to show ui in [ViewPager2Adapter] adapter
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_child, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv.text = list[position]
        holder.itemView.setBackgroundColor(
            ContextCompat.getColor(
                holder.itemView.context, when (position) {
                    0 -> R.color.home
                    1 -> R.color.logger
                    2 -> R.color.documents
                    else -> R.color.settings
                }
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }
}