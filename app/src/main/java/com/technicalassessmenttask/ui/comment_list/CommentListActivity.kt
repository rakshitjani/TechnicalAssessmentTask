package com.technicalassessmenttask.ui.comment_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.technicalassessmenttask.R
import com.technicalassessmenttask.model.comment_list.CommentsData
import com.technicalassessmenttask.model.post_list.PostData
import com.technicalassessmenttask.util.ResponseState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_comment_list.*
import kotlinx.android.synthetic.main.activity_post_list.*

@AndroidEntryPoint
class CommentListActivity : AppCompatActivity(), CommentAdapter.CommentItemListener {
    private val viewModel: CommentListViewModel by viewModels()
    private lateinit var commentListAdapter: CommentAdapter
    private var post:PostData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_list)
        title = getString(R.string.post_comments)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }

        if (intent.hasExtra("post")){
            var post:PostData? = intent.getParcelableExtra("post")
            if (post!=null){
                this.post = post

                commentPostTitle.text = post.title
                commentPostDetails.text = post.body
            }
        }
        searchViewComments.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                commentListAdapter.getFilter()?.filter(newText);
                return false
            }

        })

        setupRecyclerView()
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetCommentsEvents,post?.id.toString())

        commentsSwipeRefresh.setOnRefreshListener {
            viewModel.setStateEvent(MainStateEvent.GetCommentsEvents,post?.id.toString())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
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
        commentsSwipeRefresh.isRefreshing = isLoading
    }


    private fun setupRecyclerView() {
        commentListAdapter = CommentAdapter(this)
        rvCommentList.layoutManager = LinearLayoutManager(this)
        rvCommentList.adapter = commentListAdapter
    }

    override fun onClickedComment(comment: CharSequence) {
        Toast.makeText(this, comment, Toast.LENGTH_SHORT).show()
    }
}