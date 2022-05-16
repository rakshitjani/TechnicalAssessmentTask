package com.technicalassessmenttask.network.post_list

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostDataResponse(

    @SerializedName("userId")
    @Expose
    var userId: Int,


    @SerializedName("id")
    @Expose
    var id: Int,


    @SerializedName("title")
    @Expose
    var title: String = "",


    @SerializedName("body")
    @Expose
    var body: String = ""

)