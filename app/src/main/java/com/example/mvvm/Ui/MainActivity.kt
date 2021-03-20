package com.example.mvvm.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.Network
import com.example.mvvm.R
import com.example.mvvm.Repository.ApiHelper
import com.example.mvvm.Utils.Status
import com.example.mvvm.ViewModel.MainViewModel
import com.example.mvvm.ViewModel.ViewModelFactory
import com.example.mvvm.model.Comment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
        setupViewModel()
        setupObserver()
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this,
            ViewModelFactory(ApiHelper(Network.client))
        ).get(MainViewModel::class.java)
    }

    private fun setupUi() {
        Recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        Recycler_view.addItemDecoration(
            DividerItemDecoration(
                Recycler_view.context,
                (Recycler_view.layoutManager as LinearLayoutManager).orientation
            )
        )
        Recycler_view.adapter = adapter

    }

    private fun setupObserver() {
        viewModel.getComments().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Recycler_view.visibility = View.VISIBLE
                        progress_Bar.visibility = View.GONE
                        resource.data?.let { comments -> retrieve(comments) }
                    }
                    Status.ERROR -> {
                        Recycler_view.visibility = View.VISIBLE
                        progress_Bar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        Recycler_view.visibility = View.GONE
                        progress_Bar.visibility = View.VISIBLE
                    }
                }

            }
        })

    }

    private fun retrieve(comments: List<Comment>) {
        adapter.apply {
            commentList.addAll(comments)
            notifyDataSetChanged()
        }
    }


}