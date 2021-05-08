package com.captaindeer.yasnikas.ui.forgotPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.captaindeer.yasnikas.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.dialog_forgot_password.*
import kotlinx.android.synthetic.main.dialog_forgot_password.view.*

class ForgotPasswordDialog : DialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView: View = inflater.inflate(R.layout.dialog_forgot_password, container, false)

        rootView.btn_accept.setOnClickListener {

            FirebaseAuth.getInstance().sendPasswordResetEmail(et_email_reset.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onSuccess()
                    } else {
                        onError(task.exception.toString())
                    }
                }

        }
        return rootView
    }

    private fun onSuccess() {
        dismiss()
        Toast.makeText(requireContext(), "Verify your E-mail", Toast.LENGTH_SHORT).show()
    }

    private fun onError(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}