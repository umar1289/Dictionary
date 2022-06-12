package com.example.dictionary.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.Models.Word
import com.example.dictionary.R
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.item_rv.view.*
import kotlin.contracts.contract

class RvAdapter(val context: Context, val list: List<Word>, val itemListener: ItemListener) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(word: Word, position: Int) {
            itemView.title_word.text = word.titleWord
            itemView.animation = AnimationUtils.loadAnimation(context, R.anim.rv_anim)
            itemView.setOnClickListener { itemListener.onClick(position) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface ItemListener{
        fun onClick(position: Int)
    }
}