package com.technicalassessmenttask.model.post_list

data class PostData (
    var userId: Int,
    var id: Int,
    var title: String = "",
    var body: String = ""
)