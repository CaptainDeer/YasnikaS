package com.captaindeer.yasnikas.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.captaindeer.yasnikas.R
import com.captaindeer.yasnikas.data.remote.models.ShoesModel
import com.captaindeer.yasnikas.ui.adapters.viewHolder.ShoesDetailViewHolder
import com.squareup.picasso.Picasso

class ShoesDetailAdapter(private val shoesModel: ShoesModel) :
    RecyclerView.Adapter<ShoesDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoesDetailViewHolder {
        return ShoesDetailViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_shoes_detail, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShoesDetailViewHolder, position: Int) {
        holder.tvModel.text = shoesModel.model
        holder.tvPrice.text = shoesModel.price
        holder.tvColor.text = shoesModel.color.toString()
        holder.tvSize.text = shoesModel.size.toString()
        holder.tvDescription.text = shoesModel.description
        Picasso.get().load(shoesModel.image).into(holder.img)
    }

    override fun getItemCount(): Int = 1
}