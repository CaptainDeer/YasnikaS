package com.captaindeer.yasnikas.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.captaindeer.yasnikas.R
import com.captaindeer.yasnikas.data.remote.models.ShoesModel
import com.captaindeer.yasnikas.ui.adapters.ShoesAdapter
import com.captaindeer.yasnikas.ui.adapters.ShoesDetailAdapter
import com.captaindeer.yasnikas.ui.adapters.listeners.ListenerShoes
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeInterface.View, ListenerShoes {

    private var shoes = arrayListOf<ShoesModel>()
    private var shoesAdapter: ShoesAdapter? = null
    private var firestore: FirebaseFirestore? = null
    private var presenter: HomePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = HomePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv_home = view.findViewById<View>(R.id.rv_home) as RecyclerView
        val gridLayout = GridLayoutManager(requireContext(), 2)
        rv_home.layoutManager = gridLayout

        rv_home.setHasFixedSize(true)
        rv_home.layoutManager = gridLayout
        shoesAdapter = ShoesAdapter(shoes, this)
        rv_home.adapter = shoesAdapter

        Log.e("TAG", "Antes de usar presenter")
        presenter!!.getShoes()
        Log.e("TAG", "Saliendo de presenter")
    }

    override fun setUpdateData(shoes: ArrayList<ShoesModel>) {
        shoesAdapter!!.updateData(shoes)
    }

    override fun onSuccess() {

    }

    override fun onError(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    override fun goToShoesDetail(shoes: ShoesModel) {
        val shoesDetailAdapter = ShoesDetailAdapter(shoes)
        rv_home.setHasFixedSize(true)
        rv_home.layoutManager = LinearLayoutManager(requireContext())
        rv_home.adapter = shoesDetailAdapter
    }
}