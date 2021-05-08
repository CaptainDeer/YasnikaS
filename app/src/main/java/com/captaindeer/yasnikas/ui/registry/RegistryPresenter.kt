package com.captaindeer.yasnikas.ui.registry

import com.google.firebase.auth.FirebaseAuth

class RegistryPresenter(private val view: RegistryInterface.View): RegistryInterface.Presenter {

    override fun setNewUser(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                view.onSuccess()
            }else
                view.onError("The user could´t add to database. \n Please type a correct e-mail")
        }
    }

}