package com.technicalassessmenttask.ui.post_list

import androidx.lifecycle.*
import com.technicalassessmenttask.model.post_list.PostData
import com.technicalassessmenttask.repository.post_list.PostRepository
import com.technicalassessmenttask.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel
@Inject
constructor(
    val postRepository: PostRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState: MutableLiveData<ResponseState<List<PostData>>> = MutableLiveData()

    val dataState: LiveData<ResponseState<List<PostData>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetPostsEvents -> {
                    postRepository.getPostList()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                is MainStateEvent.None -> {
                }
            }
        }
    }

    fun setStateEventDB(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetPostsEvents -> {
                    postRepository.getPostListFromDB()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                is MainStateEvent.None -> {
                }
            }
        }
    }

}

sealed class MainStateEvent {
    object GetPostsEvents : MainStateEvent()
    object None : MainStateEvent()
}