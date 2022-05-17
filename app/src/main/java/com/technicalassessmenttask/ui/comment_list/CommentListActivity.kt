package com.technicalassessmenttask.ui.comment_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.technicalassessmenttask.R
import com.technicalassessmenttask.model.comment_list.CommentsData
import com.technicalassessmenttask.util.ResponseState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_post_list.*

@AndroidEntryPoint
class CommentListActivity : AppCompatActivity(), CommentAdapter.CommentItemListener {
    private val viewModel: CommentListViewModel by viewModels()
    private lateinit var commentListAdapter: CommentAdapter
    private var postId = "1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        if (intent.hasExtra("postID")){
            var postId:String? = intent.extras?.getString("postID")
            if (postId!=null){
                this.postId = postId
            }
        }
        setupRecyclerView()
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetCommentsEvents,postId.toString())

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.setStateEvent(MainStateEvent.GetCommentsEvents,postId.toString())
        }
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is ResponseState.Success<List<CommentsData>> -> {
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

    private fun populateRecyclerView(comments: List<CommentsData>) {
        if (comments.isNotEmpty()) commentListAdapter.setItems(ArrayList(comments))
    }

    private fun displayLoading(isLoading: Boolean) {
        swipeRefreshLayout.isRefreshing = isLoading
    }


    private fun setupRecyclerView() {
        commentListAdapter = CommentAdapter(this)
        posts_recyclerview.layoutManager = LinearLayoutManager(this)
        posts_recyclerview.adapter = commentListAdapter
    }

    override fun onClickedComment(comment: CharSequence) {
        Toast.makeText(this, comment, Toast.LENGTH_SHORT).show()
    }
}