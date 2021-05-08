package com.captaindeer.yasnikas.ui.home

import android.util.Log
import com.captaindeer.yasnikas.data.remote.models.ShoesModel
import com.captaindeer.yasnikas.ui.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore

class HomePresenter(private val view: HomeInterface.View) : HomeInterface.Presenter {

    private var firestore: FirebaseFirestore? = null

    override fun getShoes() {
        firestore = FirebaseFirestore.getInstance()
        firestore?.collection(Constants.COLLECTION_SHOES)?.get()?.addOnSuccessListener { it ->
            if (it.isEmpty) {
                return@addOnSuccessListener
            } else {
                val data = arrayListOf<ShoesModel>()
                val shoesModel: List<ShoesModel> = it.toObjects(ShoesModel::class.java)
                data.addAll(shoesModel)
                Log.e("TAG","Funcion get shoes $data")
                view.setUpdateData(data)
            }
        }
    }
}