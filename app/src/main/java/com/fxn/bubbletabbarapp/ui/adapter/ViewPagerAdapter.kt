@file:Suppress("DEPRECATION")

package com.fxn.bubbletabbarapp.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fxn.bubbletabbarapp.databinding.FragmentChildBinding
import com.fxn.bubbletabbarapp.utils.getColors

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


@Suppress("DEPRECATION")
class ViewPagerAdapter(supportFragmentManager: FragmentManager) :
    FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    var list = arrayListOf("Home", "Logger", "Documents", "Settings")


    override fun getItem(position: Int): Fragment {
        return Child.newInstance(list[position], position)
    }

    override fun getCount(): Int {
        return list.size
    }

    class Child : Fragment() {
        private var param1: String = ""
        private var param2: Int = 0
        private var _binding: FragmentChildBinding? = null
        private val binding get() = _binding!!

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            arguments?.let {
                param1 = it.getString(ARG_PARAM1) ?: ""
                param2 = it.getInt(ARG_PARAM2, 0)
            }
        }

        companion object {

            /**
             *  static method for retrieving Fragment object with param
             */
            @JvmStatic
            fun newInstance(
                param1: String,
                param2: Int
            ) =
                Child().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putInt(ARG_PARAM2, param2)
                    }
                }
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            _binding = FragmentChildBinding.inflate(inflater, container, false)
            val view = binding.root
            return view
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            binding.tv.text = param1
            binding.root.setBackgroundColor(view.context.getColors()[param2])
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
}
