package com.example.newsreporter.ui.newsreporter

import android.content.Context
import androidx.fragment.app.Fragment
import java.lang.ClassCastException

open class BaseFragment : Fragment() {
    protected lateinit var comunicatorInterface: Listener

    interface Listener {
        fun hideProgressBar()
        fun showProgressBar()
        fun hideKeyBoard()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            context as Listener
            comunicatorInterface = context
        } catch (exeption: ClassCastException) {
            println("${context.javaClass} must implement the interface ")

        }
    }
}