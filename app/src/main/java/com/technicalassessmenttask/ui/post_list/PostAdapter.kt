package com.technicalassessmenttask.ui.post_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.technicalassessmenttask.R
import com.technicalassessmenttask.model.post_list.PostData
import kotlinx.android.synthetic.main.item_posts_list.view.*

class PostAdapter(private val listener: PostItemListener) : RecyclerView.Adapter<PostViewHolder>() {


    interface PostItemListener {
        fun onClickedPost(post: PostData)
    }

    private val items: MutableList<PostData> = ArrayList<PostData>()
    private val fullList: MutableList<PostData> = ArrayList<PostData>()
    private lateinit var post: PostData

    fun setItems(items: ArrayList<PostData>) {
        this.fullList.clear()
        this.fullList.addAll(items)
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_posts_list, parent, false)
        return PostViewHolder(view, listener)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        post = items[position]
        val post = items[position]
        holder.textTitle.text = post.title
        holder.postDetails.text = post.body
        holder.itemLayout.setOnClickListener {
            listener.onClickedPost(post)
        }
    }


    fun getFilter(): Filter? {
        return filter
    }

    private var filter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: MutableList<PostData> = ArrayList()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(fullList)
            } else {
                val filterPattern: String = constraint.toString().lowercase().trim()
                for (item in fullList) {
                    if (item.title.lowercase().contains(filterPattern) || item.body.lowercase()
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
            items.addAll(results?.values as MutableList<PostData>)
            notifyDataSetChanged()
        }

    }
}

class PostViewHolder(itemView: View, private val listener: PostAdapter.PostItemListener) :
    RecyclerView.ViewHolder(itemView) {

    val itemLayout: RelativeLayout = itemView.postItemLayout
    val postItemCard: CardView = itemView.postItemCard
    val postDetails: TextView = itemView.postDetails
    val textTitle: TextView = itemView.postTitle

}

