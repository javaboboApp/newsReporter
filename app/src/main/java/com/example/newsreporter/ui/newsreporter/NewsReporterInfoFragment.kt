package com.example.newsreporter.ui.newsreporter

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.newsreporter.R
import com.example.newsreporter.models.NewReporter
import com.example.newsreporter.utils.Resource
import kotlinx.android.synthetic.main.fragment_add_news_reporter.*
import kotlinx.android.synthetic.main.fragment_news_reporter_info.*
import kotlinx.android.synthetic.main.fragment_news_reporter_info.history_editText
import kotlinx.android.synthetic.main.fragment_news_reporter_info.title_edittext
import kotlinx.android.synthetic.main.fragment_news_reporter_info.focusable_view

import org.koin.android.viewmodel.ext.android.viewModel


class NewsReporterInfoFragment : BaseFragment() {
    private  var newReporterId: Long = 0
    private val updateNewsReporterViewModel: UpdateNewsReporterViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_reporter_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpNewsReporter(view)
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_menu, menu)
    }

    private fun validate(): NewReporter? {
        val title = title_edittext.text.toString()
        val history = history_editText.text.toString()

        if (title.trim().isEmpty() || history.trim().isEmpty()) {
            return null
        }
        return NewReporter( newsReportId = newReporterId,title = title, history = history, timestamp = System.currentTimeMillis())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.update -> {
                update()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun update() {
        comunicatorInterface.hideKeyBoard()
        focusable_view.requestFocus()

        val newReporter = validate()
        if (newReporter != null) {


            updateNewsReporterViewModel.updateNewsReporter(newReporter).observe(viewLifecycleOwner, Observer { result->

                when (result.status) {
                    Resource.Status.SUCCESS -> {
                        findNavController().popBackStack()
                        comunicatorInterface.hideProgressBar()

                    }
                    Resource.Status.ERROR
                    -> {
                        showUpdatingError()
                        comunicatorInterface.hideProgressBar()
                    }
                    Resource.Status.LOADING
                    -> {
                        comunicatorInterface.showProgressBar()
                    }
                }
            })
        }
    }

    private fun showUpdatingError() {
        Toast.makeText(context, getString(R.string.error_updating_msg), Toast.LENGTH_LONG).show()

    }

    private fun setUpNewsReporter(view: View) {
        val newReporter = NewsReporterInfoFragmentArgs.fromBundle(requireArguments()).newReporter
        newReporterId = newReporter.newsReportId
        title_edittext.setText(newReporter.title ?: "")
        history_editText.setText(newReporter.history ?: "")

    }


}