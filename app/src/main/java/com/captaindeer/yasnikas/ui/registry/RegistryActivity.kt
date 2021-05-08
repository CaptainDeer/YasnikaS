package com.captaindeer.yasnikas.ui.registry

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.captaindeer.yasnikas.R
import com.captaindeer.yasnikas.ui.bases.BaseView
import com.captaindeer.yasnikas.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_registry.*

class RegistryActivity : AppCompatActivity(), RegistryInterface.View, View.OnClickListener {

    private var presenter: RegistryPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry)

        presenter = RegistryPresenter(this)

        btn_submit.setOnClickListener(this)

    }

    override fun onSuccess() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_submit -> {
                if (et_name_new.text?.isEmpty()!! || et_email_new.text?.isEmpty()!! || et_password_new.text?.isEmpty()!! || et_rpassword_new.text?.isEmpty()!!)
                    onError("Please fill all the fields")
                else if (et_password_new.text.toString() != et_rpassword_new.text.toString())
                    onError("The passwords doesn´t match")
                else if (!cb_terms_cond.isChecked)
                    onError("You need to accept terms and conditions")
                else
                    presenter?.setNewUser(et_email_new.text.toString(), et_password_new.text.toString())
            }
        }
    }
}