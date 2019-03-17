package com.marcelokmats.jayakotlinissues.issuesList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcelokmats.jayakotlinissues.R
import com.marcelokmats.jayakotlinissues.api.Issue
import kotlinx.android.synthetic.main.issues_list_item.view.*

class IssuesAdapter(
    private val items: List<Issue>,
    private val context: Context,
    private val listener: (Issue) -> Unit
) : RecyclerView.Adapter<IssuesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.issues_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item, listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(
            item: Issue,
            listener: (Issue) -> Unit
        ) = with(itemView) {
            tvTitle.text = item.title
            tvState.text = item.state

            setOnClickListener { listener(item) }
        }
    }
}
