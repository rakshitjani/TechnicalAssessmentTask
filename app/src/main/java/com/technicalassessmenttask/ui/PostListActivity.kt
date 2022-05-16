package com.technicalassessmenttask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.technicalassessmenttask.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
    }
}