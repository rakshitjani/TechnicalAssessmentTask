package com.technicalassessmenttask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import com.technicalassessmenttask.R
import com.technicalassessmenttask.repository.PostRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostListViewModel
@Inject
constructor(
    private val postRepository: PostRepository,
    private val savedStateHandle: SavedStateHandle
){

}