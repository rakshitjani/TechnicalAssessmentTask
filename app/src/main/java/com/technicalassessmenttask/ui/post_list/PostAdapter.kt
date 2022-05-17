package com.technicalassessmenttask.ui.post_list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.technicalassessmenttask.R
import com.technicalassessmenttask.model.post_list.PostData
import kotlinx.android.synthetic.main.item_posts_list.view.*
import kotlin.random.Random

class PostAdapter(private val listener: PostItemListener) : RecyclerView.Adapter<PostViewHolder>() {


    interface PostItemListener {
        fun onClickedPost(blogTitle: CharSequence)
    }

    private val items = ArrayList<PostData>()
    private lateinit var post: PostData

    fun setItems(items: ArrayList<PostData>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_posts_list, parent, false)
        return PostViewHolder(view, listener)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        post = items[position]
        val post = items[position]
        holder.textTitle.text = post.title
        holder.itemLayout.setOnClickListener {
            listener.onClickedPost(post.id.toString())
        }
    }
}

class PostViewHolder(itemView: View, private val listener: PostAdapter.PostItemListener) :
    RecyclerView.ViewHolder(itemView) {

    val itemLayout: RelativeLayout = itemView.postItemLayout
    val postItemCard: CardView = itemView.postItemCard
    val textTitle: TextView = itemView.postTitle

}

