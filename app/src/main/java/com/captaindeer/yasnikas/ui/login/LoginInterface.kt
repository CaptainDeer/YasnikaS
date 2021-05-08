package com.captaindeer.yasnikas.ui.login

import com.captaindeer.yasnikas.ui.bases.BaseView

interface LoginInterface {

    interface Presenter {
        fun goToLogin(email: String, password: String)
    }

    interface View: BaseView {

    }

}