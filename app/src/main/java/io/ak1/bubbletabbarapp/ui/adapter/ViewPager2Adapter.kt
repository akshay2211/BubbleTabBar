package io.ak1.bubbletabbarapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.ak1.bubbletabbarapp.R
import io.ak1.bubbletabbarapp.databinding.FragmentChildBinding

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
    inner class ViewHolder(private val fragmentChildBinding: FragmentChildBinding) :
        RecyclerView.ViewHolder(fragmentChildBinding.root) {
        fun bind() {
            fragmentChildBinding.tv.text = list[adapterPosition]
            fragmentChildBinding.root.setBackgroundColor(
                ContextCompat.getColor(
                    fragmentChildBinding.root.context, when (adapterPosition) {
                        0 -> R.color.home
                        1 -> R.color.logger
                        2 -> R.color.documents
                        else -> R.color.settings
                    }
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(FragmentChildBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()
    override fun getItemCount() = list.size
}