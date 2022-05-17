package com.technicalassessmenttask.ui.comment_list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.technicalassessmenttask.R
import com.technicalassessmenttask.model.comment_list.CommentsData
import kotlinx.android.synthetic.main.item_posts_list.view.*
import kotlin.random.Random

class CommentAdapter(private val listener: CommentItemListener) : RecyclerView.Adapter<CommentViewHolder>() {

    interface CommentItemListener {
        fun onClickedComment(blogTitle: CharSequence)
    }

    private val items = ArrayList<CommentsData>()
    private lateinit var comments: CommentsData

    fun setItems(items: ArrayList<CommentsData>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_posts_list, parent, false)
        return CommentViewHolder(view, listener)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        comments = items[position]
        val post = items[position]
        holder.textTitle.text = post.name
    }
}

class CommentViewHolder(itemView: View, private val listener: CommentAdapter.CommentItemListener) :
    RecyclerView.ViewHolder(itemView),
    View.OnClickListener {

    val itemLayout: RelativeLayout = itemView.postItemLayout
    val postItemCard: CardView = itemView.postItemCard
    val textTitle: TextView = itemView.postTitle

    init {
        itemLayout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        listener.onClickedComment(textTitle.text)
    }
}

