package com.ifs21008.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21008.dinopedia.databinding.ItemRowFamilyBinding

class ListFamilyAdapter(private val listFamily: ArrayList<Family>) :
    RecyclerView.Adapter<ListFamilyAdapter.ListViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowFamilyBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextIl8n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val family= listFamily[position]
        holder.binding.ivItemFamily.setImageResource(family.family_icon)
        holder.binding.tvItemFamily.text = family.family_name
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listFamily[position])
        }
    }

    override fun getItemCount(): Int = listFamily.size

    class ListViewHolder(var binding: ItemRowFamilyBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Family)
    }
}