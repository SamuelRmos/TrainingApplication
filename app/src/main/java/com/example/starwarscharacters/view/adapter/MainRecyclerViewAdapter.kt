package com.example.starwarscharacters.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarscharacters.databinding.ItemLayoutBinding
import com.example.starwarscharacters.model.People
import java.util.*

class MainRecyclerViewAdapter(val context: Context, list: MutableList<People>) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder>() {

    var mList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    fun updateListItems(updateList: MutableList<People>) {
        mList.clear()
        mList = updateList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = mList[position]
        holder.apply {
            bind(item)
            itemView.tag = position
        }
    }

    override fun getItemCount(): Int = mList.size

    class MainViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: People) {
            binding.apply {
                people = item
            }
        }
    }
}