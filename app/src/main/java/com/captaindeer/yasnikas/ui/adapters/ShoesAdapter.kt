package com.captaindeer.yasnikas.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.captaindeer.yasnikas.R
import com.captaindeer.yasnikas.data.remote.models.ShoesModel
import com.captaindeer.yasnikas.ui.adapters.listeners.ListenerShoes
import com.captaindeer.yasnikas.ui.adapters.viewHolder.ShoesViewHolder
import com.squareup.picasso.Picasso

class ShoesAdapter(private var shoes: ArrayList<ShoesModel>, private val listenerShoes: ListenerShoes): RecyclerView.Adapter<ShoesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoesViewHolder {
        return ShoesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_shoes, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShoesViewHolder, position: Int) {
        val shoe = shoes[position]
        Picasso.get().load(shoe.image).into(holder.img)
        holder.itemView.setOnClickListener{
            listenerShoes.goToShoesDetail(shoe)
        }
    }

    override fun getItemCount(): Int = shoes.size

    fun updateData(shoes: ArrayList<ShoesModel>){
        this.shoes = shoes
        notifyDataSetChanged()
    }
}