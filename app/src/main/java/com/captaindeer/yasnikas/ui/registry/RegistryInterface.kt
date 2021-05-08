package com.captaindeer.yasnikas.ui.registry

import com.captaindeer.yasnikas.ui.bases.BaseView

interface RegistryInterface {

    interface Presenter{
        fun setNewUser(email:String, password:String)
    }

    interface View: BaseView{

    }

}