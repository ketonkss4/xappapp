package com.xappdem.pmd.xappdem.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.xappdem.pmd.xappdem.R
import com.xappdem.pmd.xappdem.data.RepoResult
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 */
class RepoListAdapter : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

    private val itemClickSubject: PublishSubject<RepoResult> = PublishSubject.create()
    private val repoList: ArrayList<RepoResult> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_list_item, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val repoData = repoList[position]
        viewHolder.itemView.setOnClickListener { itemClickSubject.onNext(repoList[position]) }
        viewHolder.bind(repoData.name, repoData.author)
    }

    fun refreshList(list: ArrayList<RepoResult>) {
        if (itemCount != 0) repoList.clear()
        repoList.addAll(list)
        notifyDataSetChanged()
    }

    fun observeClicks(): Observable<RepoResult> {
        return itemClickSubject
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val repoTitleView: TextView = itemView.findViewById(R.id.repo_title)
        private val repoAuthorView: TextView = itemView.findViewById(R.id.author)

        fun bind(title: String, author: String) {
            repoTitleView.text = title
            repoAuthorView.text = author
        }

    }
}