package com.example.dictionary.Adapters

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.example.dictionary.EnglishFragment
import com.example.dictionary.UzbekFragment

class VpAdapter(fragmentActivity: FragmentActivity?)
    : FragmentStateAdapter(fragmentActivity!!) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        Log.d("onPosition", "$position")
        return when (position) {
            0 -> UzbekFragment()
            1 -> EnglishFragment()
            else -> UzbekFragment()
        }
    }

    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        Log.d("onBindPosition", "$position")
    }
}