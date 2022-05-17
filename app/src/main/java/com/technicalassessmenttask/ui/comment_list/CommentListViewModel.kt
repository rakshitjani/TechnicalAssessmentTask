package com.technicalassessmenttask.ui.comment_list

import androidx.lifecycle.*
import com.technicalassessmenttask.model.comment_list.CommentsData
import com.technicalassessmenttask.repository.comment_list.CommentRepository
import com.technicalassessmenttask.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentListViewModel
@Inject
constructor(
    private val commentRepository: CommentRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState: MutableLiveData<ResponseState<List<CommentsData>>> = MutableLiveData()

    val dataState: LiveData<ResponseState<List<CommentsData>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent, postID: String) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetCommentsEvents -> {
                    commentRepository.getCommentList(postID)
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

    fun setStateEventDB(mainStateEvent: MainStateEvent, postID: Int?) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetCommentsEvents -> {
                    commentRepository.getCommentListFromDB(postID)
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
    object GetCommentsEvents : MainStateEvent()
    object None : MainStateEvent()
}