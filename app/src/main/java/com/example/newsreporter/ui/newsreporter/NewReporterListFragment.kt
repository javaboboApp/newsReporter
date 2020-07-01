package com.example.newsreporter.ui.newsreporter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.newsreporter.R
import com.example.newsreporter.models.NewReporter
import com.example.newsreporter.utils.CustomItemTouchHelper
import com.example.newsreporter.utils.Resource.Status.*
import kotlinx.android.synthetic.main.fragment_new_reporter_list.*
import org.koin.android.viewmodel.ext.android.viewModel


class NewReporterListFragment : BaseFragment() {


    private lateinit var mAdapter: NewReporterItemAdapter
    private val newsReporterListViewModel: NewsReporterListViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_reporter_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.requestFocus()
        initAdapter()
        initRecyclerView()
        subscribeObserver()
        initListeners()


    }

    private fun initListeners() {
        add_news_reporters.setOnClickListener {
            findNavController().navigate(R.id.action_newReporterListFragment_to_addNewsReporterFragment)
        }
    }

    private fun initAdapter() {
        mAdapter = NewReporterItemAdapter(object : NewReporterItemAdapter.Listener {


            //on remove item called
            override fun remove(newReporter: NewReporter) {
                newsReporterListViewModel.remove(newReporter).observe(viewLifecycleOwner, Observer {
                    when (it.status) {
                        SUCCESS -> {
                            //do nothing
                            comunicatorInterface.hideProgressBar()
                        }
                        ERROR -> {
                            //do nothing
                            comunicatorInterface.hideProgressBar()
                        }
                        LOADING -> {
                            comunicatorInterface.showProgressBar()
                        }
                    }
                })
            }

            override fun onClickItem(newReporter: NewReporter) {
                val action = NewReporterListFragmentDirections.actionNewReporterListFragmentToNewsReporterInfoFragment(newReporter)
                findNavController().navigate(action)
            }

        })
    }

    private fun initRecyclerView() {
        news_reporter_recyclerview.apply {
            adapter = mAdapter
        }
        val itemTouchHelper = ItemTouchHelper(CustomItemTouchHelper(mAdapter))
        itemTouchHelper.attachToRecyclerView(news_reporter_recyclerview)

    }

    private fun subscribeObserver() {
        newsReporterListViewModel.getNewsReportedList()
            .observe(viewLifecycleOwner, Observer { response ->
                when (response.status) {
                    SUCCESS -> {
                        response.data?.peekContent()?.let { responseList ->
                            if (responseList.isEmpty()) {
                                showNotDataText()
                            } else {
                                hideNotDataText()
                            }
                            mAdapter.list = responseList
                        }
                        comunicatorInterface.hideProgressBar()
                    }
                    LOADING -> {
                        comunicatorInterface.showProgressBar()
                    }
                    ERROR -> {
                        response.data?.getContentIfNotHandled().let {
                            //avoid to show error msg twice when the screen is rotated
                            showErrorMsg()
                        }

                        comunicatorInterface.hideProgressBar()

                    }
                }

            })
    }

    private fun hideNotDataText() {
        no_data_textview.visibility = GONE
    }

    private fun showNotDataText() {
        no_data_textview.visibility = VISIBLE
    }

    private fun showErrorMsg() {
        Toast.makeText(context, getString(R.string.msg_error_retriving), Toast.LENGTH_LONG).show()
    }


}