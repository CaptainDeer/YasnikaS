package com.captaindeer.yasnikas.ui.login

import com.captaindeer.yasnikas.ui.utils.ValidEmail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LoginPresenter(private val view: LoginInterface.View): LoginInterface.Presenter {

    private var auth: FirebaseAuth? = null

    override fun goToLogin(email: String, password: String) {
        when {
            ValidEmail.isEmailValid(email) -> {
                view.onError("Please type an email")
            }
            password.isEmpty() -> {
                view.onError("Please type any password")
            }
            else -> {
                auth = FirebaseAuth.getInstance()
                auth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        view.onSuccess()
                    }else{
                        view.onError(task.exception.toString())
                    }
                }
            }
        }


    }

}