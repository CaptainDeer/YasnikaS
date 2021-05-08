package com.captaindeer.yasnikas.ui.addDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.captaindeer.yasnikas.R
import kotlinx.android.synthetic.main.addproduct_popup.view.*

class AddDetailFragment : DialogFragment() {

    var colors: ArrayList<String>? = null
    var sizes: ArrayList<String>? = null
    var stock: Int? = null

    var colorSelected: String? = null
    var sizeSelected: String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.addproduct_popup, container, false)

        colors = arguments?.getStringArrayList("colors")
        sizes = arguments?.getStringArrayList("sizes")
        stock = arguments?.getInt("stock")

        val item = rootView.findViewById(R.id.appCompatTextView7) as TextView
        item.text = stock.toString()


//        appCompatTextView7.text = stock.toString()

        val spinnerColor = rootView.spinner_color.findViewById(R.id.spinner_color) as Spinner
        val spinnerSize = rootView.spinner_size.findViewById(R.id.spinner_size) as Spinner

        val arrayColors = resources.getStringArray(R.array.colors)
        val arraySizes = resources.getStringArray(R.array.sizes)

        val colorAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            arrayColors
        )
        val sizesAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            arraySizes
        )

        spinnerColor.adapter = colorAdapter
        spinnerSize.adapter = sizesAdapter

        spinnerColor.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                colorSelected = arrayColors[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        spinnerSize.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                sizeSelected = arraySizes[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        rootView.btn_ok.setOnClickListener {
            colors?.set(stock!!, colorSelected)
            sizes?.set(stock!!, sizeSelected)
            //       Toast.makeText(requireContext(),"Informacion --- ${colors?.get()}",Toast.LENGTH_LONG).show()
            dismiss()
        }
        return rootView
    }
}