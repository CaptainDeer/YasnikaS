package com.captaindeer.yasnikas.ui.newProduct

import android.net.Uri
import com.captaindeer.yasnikas.data.remote.models.ShoesModel
import com.captaindeer.yasnikas.ui.bases.BaseView

interface NewProductInterface {

    interface Presenter {
        fun createNewPost(shoe: ShoesModel, file: Uri)
    }

    interface View : BaseView {

    }

}