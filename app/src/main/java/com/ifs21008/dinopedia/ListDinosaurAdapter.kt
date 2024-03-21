package com.ifs21008.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21008.dinopedia.databinding.ItemRowDinoBinding

class ListDinosaurAdapter(private val listDinosaurus: ArrayList<Dinosaurus>):
    RecyclerView.Adapter<ListDinosaurAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowDinoBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dino = listDinosaurus[position]
        holder.binding.ivItemFamily.setImageResource(dino.dino_icon)
        holder.binding.tvItemFamily.text = dino.dino_name
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listDinosaurus[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listDinosaurus.size

    class ListViewHolder(var binding: ItemRowDinoBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Dinosaurus)
    }
}