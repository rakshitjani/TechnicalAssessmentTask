package com.technicalassessmenttask.ui.comment_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.technicalassessmenttask.R
import com.technicalassessmenttask.model.comment_list.CommentsData
import com.technicalassessmenttask.model.post_list.PostData
import kotlinx.android.synthetic.main.item_comments_list.view.*
import kotlinx.android.synthetic.main.item_posts_list.view.*

class CommentAdapter(private val listener: CommentItemListener) : RecyclerView.Adapter<CommentViewHolder>() {

    interface CommentItemListener {
        fun onClickedComment(blogTitle: CharSequence)
    }

    private val items:MutableList<CommentsData> = ArrayList<CommentsData>()
    private val fullList:MutableList<CommentsData> = ArrayList<CommentsData>()
    private lateinit var comments: CommentsData

    fun setItems(items: ArrayList<CommentsData>) {
        this.fullList.clear()
        this.fullList.addAll(items)
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comments_list, parent, false)
        return CommentViewHolder(view, listener)
    }

    override fun getItemCount(): Int = items.size

    fun getFilter(): Filter? {
        return filter
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        comments = items[position]
        val comment = items[position]
        holder.commentName.text = comment.name
        holder.commentEmail.text = comment.email
        holder.comment.text = comment.body
    }

    private var filter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: MutableList<CommentsData> = ArrayList()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(fullList)
            } else {
                val filterPattern: String = constraint.toString().lowercase().trim()
                for (item in fullList) {
                    if (item.name.lowercase().contains(filterPattern) || item.body.lowercase()
                            .contains(filterPattern)
                    ) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(p0: CharSequence?, results: FilterResults?) {
            items.clear()
            items.addAll(results?.values as MutableList<CommentsData>)
            notifyDataSetChanged()
        }

    }
}

class CommentViewHolder(itemView: View, private val listener: CommentAdapter.CommentItemListener) :
    RecyclerView.ViewHolder(itemView) {

    val commentName:TextView = itemView.commentName
    val commentEmail:TextView = itemView.commentEmail
    val comment:TextView = itemView.comment




}

