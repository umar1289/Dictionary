package com.example.dictionary

import android.os.Bundle
import android.os.WorkSource
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.dictionary.Adapters.RvAdapter
import com.example.dictionary.Adapters.VpAdapter
import com.example.dictionary.Models.Word
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.tab_item.*
import kotlinx.android.synthetic.main.tab_item.view.*
import java.util.*
import kotlin.collections.ArrayList

var list: Int = 0
class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    lateinit var listTab: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(LayoutInflater.from(context))
        listTab = ArrayList()
        listTab.add("Uzbek")
        listTab.add("English")

        binding.vp.adapter = VpAdapter(activity)
        TabLayoutMediator(
            binding.tab,
            binding.vp,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    val tabview =
                        LayoutInflater.from(context).inflate(R.layout.tab_item, null, false)
                    tab.customView = tabview
                    tabview.tab_txt.text = listTab[position]

                    binding.vp.setCurrentItem(tab.position, true)
                }
            }).attach()

        binding.search.setOnClickListener {
            findNavController().navigate(R.id.searchFragment, bundleOf("list" to list))
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        list = if (binding.vp.currentItem == 0) {
            0
        } else {
            1
        }
    }

}