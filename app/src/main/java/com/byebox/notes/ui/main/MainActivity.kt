package com.byebox.notes.ui.main

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.byebox.notes.R
import com.byebox.notes.ui.notes.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), BaseFragment.Listener {
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            //back arrow...
            android.R.id.home -> {
                navController.popBackStack(R.id.newReporterListFragment, false);
            }

        }
        return super.onOptionsItemSelected(item)

    }

    override fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideKeyBoard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(
                Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager
                .hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

}