package com.technicalassessmenttask.ui.post_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.technicalassessmenttask.R
import com.technicalassessmenttask.model.post_list.PostData
import com.technicalassessmenttask.ui.comment_list.CommentListActivity
import com.technicalassessmenttask.util.ResponseState
import com.technicalassessmenttask.util.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_post_list.*

@AndroidEntryPoint
class PostListActivity : AppCompatActivity(), PostAdapter.PostItemListener {
    private val viewModel: PostListViewModel by viewModels()
    private lateinit var postListAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        title = getString(R.string.posts)

        searchViewPosts.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                searchViewPosts.clearFocus();
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                postListAdapter.getFilter()?.filter(newText);
                return false
            }

        })
        setupRecyclerView()
        subscribeObservers()
        if (Utils.isNetworkAvailable(this)) {
            viewModel.setStateEvent(MainStateEvent.GetPostsEvents)
        } else {
            viewModel.setStateEventDB(MainStateEvent.GetPostsEvents)
        }

        swipeRefreshLayout.setOnRefreshListener {
            if (Utils.isNetworkAvailable(this)) {
                viewModel.setStateEvent(MainStateEvent.GetPostsEvents)
            } else {
                viewModel.setStateEventDB(MainStateEvent.GetPostsEvents)
            }
        }
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is ResponseState.Success<List<PostData>> -> {
                    displayLoading(false)
                    if (dataState.data.isNotEmpty()) {
                        noPosts.visibility = View.GONE
                        populateRecyclerView(dataState.data)
                    } else {
                        noPosts.visibility = View.VISIBLE
                    }
                }
                is ResponseState.Loading -> {
                    displayLoading(true)
                }
                is ResponseState.Error -> {
                    displayLoading(false)
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

    override fun onClickedPost(post: PostData) {
        val intent = Intent(this, CommentListActivity::class.java)
        intent.putExtra("post", post)
        startActivity(intent)
    }
}