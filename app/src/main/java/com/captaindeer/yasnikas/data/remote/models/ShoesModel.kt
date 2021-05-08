package com.captaindeer.yasnikas.data.remote.models

data class ShoesModel(
    var category: String = "",
    val color: ArrayList<String> = arrayListOf(),
    val description: String = "",
    var image: String = "",
    val model: String = "",
    val price: String = "",
    val size: ArrayList<String> = arrayListOf(),
    val stock: String = "",
    val sku: String = ""
) {

}