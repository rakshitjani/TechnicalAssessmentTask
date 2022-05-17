package com.technicalassessmenttask.database.comment_list

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "commentsDao")
data class CommentsListCacheEntity(


    @ColumnInfo(name = "postId")
    var postId: Int,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "body")
    var body: String,


)