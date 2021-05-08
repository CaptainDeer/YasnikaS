package com.captaindeer.yasnikas.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.captaindeer.yasnikas.R
import com.captaindeer.yasnikas.ui.bases.BaseView
import com.captaindeer.yasnikas.ui.forgotPassword.ForgotPasswordDialog
import com.captaindeer.yasnikas.ui.main.MainActivity
import com.captaindeer.yasnikas.ui.registry.RegistryActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginInterface.View, View.OnClickListener {

    private var auth: FirebaseAuth? = null
    private var presenter: LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)
        auth = FirebaseAuth.getInstance()


        tv_registry.setOnClickListener(this)
        btn_log.setOnClickListener(this)
        forgot.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        if (auth?.currentUser != null){
            onSuccess()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_registry -> {
                startActivity(Intent(this, RegistryActivity::class.java))
            }
            R.id.btn_log -> {
                presenter?.goToLogin(et_email.text.toString(),et_password.text.toString())
            }
            R.id.forgot -> {
                ForgotPasswordDialog().show(supportFragmentManager,"customDialog")


            }
        }
    }

    override fun onSuccess() {
        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity()
    }

    override fun onError(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}