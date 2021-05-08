package com.captaindeer.yasnikas.ui.newProduct

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.captaindeer.yasnikas.R
import com.captaindeer.yasnikas.data.remote.models.ShoesModel
import com.captaindeer.yasnikas.ui.main.MainActivity
import com.captaindeer.yasnikas.ui.utils.Constants
import kotlinx.android.synthetic.main.activity_new_product.*
import kotlinx.android.synthetic.main.addproduct_popup.*
import java.io.IOException

class NewProductActivity : AppCompatActivity(), NewProductInterface.View, View.OnClickListener {

    private lateinit var presenter: NewProductPresenter
    private lateinit var shoe: ShoesModel
    private var filePath: Uri? = null
    var colors: ArrayList<String>? = arrayListOf()
    var sizes: ArrayList<String>? = arrayListOf()
    var nameColor: String? = null
    var nameSize: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_product)

        presenter = NewProductPresenter(this)

        btn_send.setOnClickListener(this)
        img_newProduct.setOnClickListener(this)
        btn_detail.setOnClickListener(this)

        btn_send.isEnabled = false

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.REQUEST_22 && resultCode == RESULT_OK && data != null) {
            filePath = data.data

            try {
                val bitMap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                img_newProduct.setImageBitmap(bitMap)
            } catch (e: IOException) {
                Log.e("TAG", e.printStackTrace().toString())
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_detail -> {
                var limit = 0
                val stock = et_stock.text.toString()
                val stockInt = Integer.parseInt(stock)
                btn_send.isEnabled = stockInt > 0

                do {
                    val dialogStock =
                        LayoutInflater.from(this).inflate(R.layout.addproduct_popup, null)
                    val alertDialog = AlertDialog.Builder(this).setView(dialogStock).show()

                    val arrayColors = resources.getStringArray(R.array.colors)
                    val arraySizes = resources.getStringArray(R.array.sizes)

                    alertDialog.appCompatTextView7.text = limit.toString()

                    val colorAdapter = ArrayAdapter(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        arrayColors
                    )
                    val sizesAdapter = ArrayAdapter(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        arraySizes
                    )

                    alertDialog.spinner_color.adapter = colorAdapter
                    alertDialog.spinner_size.adapter = sizesAdapter

                    alertDialog.spinner_color.onItemSelectedListener = object :
                        AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            nameColor = arrayColors[position].toString()
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                        }
                    }

                    alertDialog.spinner_size.onItemSelectedListener = object :
                        AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            nameSize = arraySizes[position].toString()
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                        }
                    }

                    alertDialog.btn_ok.setOnClickListener {
                        alertDialog.dismiss()

                        colors!!.add(nameColor.toString())
                        sizes!!.add(nameSize.toString())
                        Log.e("TAG", "Enter de dialog $colors")
                        Log.e("TAG", "Enter de dialog $sizes")

                    }
                    limit++
                } while (limit != stockInt)
            }

            R.id.btn_send -> {

                shoe = ShoesModel(
                    et_category.text.toString(),
                    colors!!,
                    et_description.text.toString(),
                    "",
                    et_model.text.toString(),
                    et_price.text.toString(),
                    sizes!!,
                    et_stock.text.toString(),
                    et_sku.text.toString()
                )
                presenter.createNewPost(shoe, filePath!!)

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

            R.id.img_newProduct -> {
                chooseImage()
            }
        }

    }
    //Here you can open your gallery to select your photo
    private fun chooseImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_PICK
        startActivityForResult(
            Intent.createChooser(intent, "Select Picture"),
            Constants.REQUEST_22
        )
    }

    override fun onSuccess() {
        TODO("Not yet implemented")
    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}
