package com.captaindeer.yasnikas.ui.home

import com.captaindeer.yasnikas.data.remote.models.ShoesModel
import com.captaindeer.yasnikas.ui.bases.BaseView

interface HomeInterface {

    interface Presenter {
        fun getShoes()
    }

    interface View : BaseView {
        fun setUpdateData(shoes: ArrayList<ShoesModel>)
    }

}