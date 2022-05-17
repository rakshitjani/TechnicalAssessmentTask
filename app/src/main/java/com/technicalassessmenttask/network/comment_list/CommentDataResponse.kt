package com.technicalassessmenttask.network.comment_list

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CommentDataResponse(

    @SerializedName("postId")
    @Expose
    var postId: Int,


    @SerializedName("id")
    @Expose
    var id: Int,


    @SerializedName("name")
    @Expose
    var name: String = "",


    @SerializedName("email")
    @Expose
    var email: String = "",


    @SerializedName("body")
    @Expose
    var body: String = ""

)