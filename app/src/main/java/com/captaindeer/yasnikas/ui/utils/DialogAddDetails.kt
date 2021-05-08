package com.captaindeer.yasnikas.ui.utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.captaindeer.yasnikas.R

/**
 * Esta estructura se utiliza para mandar llamar un dialog de carga (por ejemplo)
 */


@SuppressLint("StaticFieldLeak")
object DialogAddDetails {

    private lateinit var dialog: Dialog
    private lateinit var number: TextView
    private lateinit var btn: Button

    fun showDialog(context: Context, stock: Int) {

        dialog = Dialog(context)
      //  dialog.setCancelable(false)
        dialog.setContentView(R.layout.addproduct_popup)

        number = dialog.findViewById(R.id.appCompatTextView7)
        number.text = stock.toString()

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog.show()

        btn = dialog.findViewById(R.id.btn_ok)

        btn.setOnClickListener {
            hideDialog()
        }

    }

    fun hideDialog() {
        btn.setOnClickListener {
            dialog.dismiss()
        }
    }

}