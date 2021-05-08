package com.captaindeer.yasnikas.ui.adapters.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.captaindeer.yasnikas.R

class ShoesDetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val img = view.findViewById(R.id.img_detail_shoes) as ImageView
    val tvModel = view.findViewById(R.id.tv_model) as TextView
    val tvPrice = view.findViewById(R.id.tv_price) as TextView
    val tvColor = view.findViewById(R.id.tv_color) as TextView
    val tvSize = view.findViewById(R.id.tv_size) as TextView
    val tvDescription = view.findViewById(R.id.tv_description) as TextView

}