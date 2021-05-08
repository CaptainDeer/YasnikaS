package com.captaindeer.yasnikas.ui.main

import androidx.fragment.app.Fragment
import com.captaindeer.yasnikas.data.remote.models.ShoesModel
import com.captaindeer.yasnikas.ui.bases.BaseView

interface MainInterface {

    interface View : BaseView{
        fun changeFragment(fragment: Fragment)
    }

    interface Presenter {

    }

}