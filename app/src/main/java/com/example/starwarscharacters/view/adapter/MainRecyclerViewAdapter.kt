package com.example.starwarscharacters.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarscharacters.databinding.ItemLayoutBinding
import com.example.starwarscharacters.model.People

class MainRecyclerViewAdapter(private var list: MutableList<People>) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    fun updateListItems(updateList: MutableList<People>) {
        list.clear()
        list = updateList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = list[position]
        holder.apply {
            bind(item)
            itemView.tag = position
        }
    }

    override fun getItemCount(): Int = list.size

    class MainViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: People) {
            binding.apply {
                people = item
            }
        }
    }

}