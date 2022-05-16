package com.technicalassessmenttask.database.post_list

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "postsDao")
data class PostListCacheEntity(


    @ColumnInfo(name = "userId")
    var userId: Int,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "body")
    var body: String,


)