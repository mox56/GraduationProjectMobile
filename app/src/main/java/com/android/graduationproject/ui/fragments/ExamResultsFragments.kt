package com.android.graduationproject.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.graduationproject.R
import com.android.graduationproject.Resource
import com.android.graduationproject.StudentAdapter
import com.android.graduationproject.data.ExamResult
import com.android.graduationproject.view.MainActivity
import com.android.graduationproject.view_model.MainViewModel


class ExamResultsFragments: Fragment (R.layout.fragment_exam_results){

    lateinit var viewModel: MainViewModel
    lateinit var examAdapter: StudentAdapter
    lateinit var rvExamResult: RecyclerView
    val TAG= "ExamResultFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as MainActivity).viewModel
        setupRecyclerView()


        viewModel.examresult.observe(viewLifecycleOwner, Observer{response ->
            when(response){
                is Resource.Success->{
                    response.data?.let {examResponse ->
                        examAdapter.differ.equals(examResponse.studentIndex)

                    }
                }
                is Resource.Error->{
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }

        })
    }

private fun showProgressBar(){


}

    private fun setupRecyclerView(){
        var rvExamResult = rvExamResult
        examAdapter = StudentAdapter()

        rvExamResult.apply {
            adapter = examAdapter
            layoutManager = LinearLayoutManager(activity)

        }
    }
}