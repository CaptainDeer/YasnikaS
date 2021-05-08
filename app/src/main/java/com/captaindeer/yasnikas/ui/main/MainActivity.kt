package com.captaindeer.yasnikas.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.captaindeer.yasnikas.R
import com.captaindeer.yasnikas.ui.home.HomeFragment
import com.captaindeer.yasnikas.ui.login.LoginActivity
import com.captaindeer.yasnikas.ui.newProduct.NewProductActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainInterface.View, SwipeRefreshLayout.OnRefreshListener {

    private var auth: FirebaseAuth? = null
    private lateinit var toggle: ActionBarDrawerToggle
    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipe_refresh.setOnRefreshListener(this)

        setSupportActionBar(toolbar)
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val homeFragment = HomeFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_main, homeFragment)
            commit()
        }

        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setHomeButtonEnabled(true)

        nav_drawer.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fl_main, homeFragment)
                        addToBackStack(null)
                        commit()
                    }
                }
                R.id.brands -> onError("Brands")
                R.id.cart -> onError("Cart")
                R.id.others -> startActivity(Intent(this, NewProductActivity::class.java))
                R.id.sign_out -> {
                    auth?.signOut()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finishAffinity()
                }
            }
            true
        }
    }

    override fun onBackPressed() {
        if (this.drawer.isDrawerOpen(GravityCompat.START))
            this.drawer.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    override fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            FragmentTransaction.TRANSIT_FRAGMENT_OPEN
            replace(R.id.fl_main, fragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onSuccess() {
    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onRefresh() {
        changeFragment(HomeFragment())
        swipe_refresh.isRefreshing = false
    }

}