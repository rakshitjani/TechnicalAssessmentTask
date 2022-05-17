package com.technicalassessmenttask.model.comment_list

data class CommentsData(
    var postId: Int,
    var id: Int,
    var name: String = "",
    var email: String = "",
    var body: String = ""
)