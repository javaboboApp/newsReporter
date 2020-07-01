package com.example.newsreporter.ui.newsreporter

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.newsreporter.R
import com.example.newsreporter.models.NewReporter
import com.example.newsreporter.utils.Resource.Status.*
import kotlinx.android.synthetic.main.fragment_add_news_reporter.*
import org.koin.android.viewmodel.ext.android.viewModel


class AddNewsReporterFragment : BaseFragment() {
    private val addNewsReporterViewModel: AddNewsReporterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_news_reporter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }



    private fun insert() {
        val newReporter = validate()
        if (newReporter == null) {
            showValidationError()
            return
        }

        addAndObserver(newReporter)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.insert_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.insert -> {
                insert()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun addAndObserver(validate: NewReporter) {
        addNewsReporterViewModel.insertNewsReportedList(validate)
            .observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    SUCCESS -> {
                        findNavController().popBackStack()
                        comunicatorInterface.hideProgressBar()
                        comunicatorInterface.hideKeyBoard()

                    }
                    ERROR
                    -> {
                        showInsertionError()
                        comunicatorInterface.hideProgressBar()
                    }
                    LOADING
                    -> {
                        comunicatorInterface.showProgressBar()
                    }
                }
            })
    }

    private fun showValidationError() {
        Toast.makeText(context, getString(R.string.not_empty_field_error), Toast.LENGTH_LONG).show()
    }


    private fun showInsertionError() {
        Toast.makeText(context, getString(R.string.error_inserting_msg), Toast.LENGTH_LONG).show()
    }

    private fun validate(): NewReporter? {
        val title = title_edittext.text.toString()
        val history = history_editText.text.toString()

        if (title.trim().isEmpty() || history.trim().isEmpty()) {
          return  null
        }
        return NewReporter(title = title, history = history, timestamp = System.currentTimeMillis())
    }
}