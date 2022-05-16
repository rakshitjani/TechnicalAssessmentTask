package com.technicalassessmenttask.ui.post_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.technicalassessmenttask.R
import com.technicalassessmenttask.model.post_list.PostData
import com.technicalassessmenttask.util.ResponseState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_post_list.*

@AndroidEntryPoint
class PostListActivity : AppCompatActivity(), PostAdapter.PostItemListener {
    private val viewModel: PostListViewModel by viewModels()
    private lateinit var postListAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        setupRecyclerView()
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetPostsEvents)

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.setStateEvent(MainStateEvent.GetPostsEvents)
        }
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is ResponseState.Success<List<PostData>> -> {
                    Log.e("","")
                    displayLoading(false)
                    populateRecyclerView(dataState.data)
                }
                is ResponseState.Loading -> {
                    Log.e("","")
                    displayLoading(true)
                }
                is ResponseState.Error -> {
                    Log.e("","")
                    displayLoading(false)
//                    displayError(dataState.exception.message)
                }
            }
        })
    }

    private fun populateRecyclerView(blogs: List<PostData>) {
        if (blogs.isNotEmpty()) postListAdapter.setItems(ArrayList(blogs))
    }

    private fun displayLoading(isLoading: Boolean) {
        swipeRefreshLayout.isRefreshing = isLoading
    }


    private fun setupRecyclerView() {
        postListAdapter = PostAdapter(this)
        posts_recyclerview.layoutManager = LinearLayoutManager(this)
        posts_recyclerview.adapter = postListAdapter
    }

    override fun onClickedBlog(postTitle: CharSequence) {
        Toast.makeText(this, postTitle, Toast.LENGTH_SHORT).show()
    }
}