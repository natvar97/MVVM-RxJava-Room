package com.indialone.mvvmandrxjava.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.indialone.mvvmandrxjava.databinding.ItemNewsLayoutBinding
import com.indialone.mvvmandrxjava.network.Entity.Articles

class NewsRecyclerAdapter : RecyclerView.Adapter<NewsRecyclerAdapter.NewsRecyclerViewHolder>() {

    private val news = ArrayList<Articles>()

    class NewsRecyclerViewHolder(itemView: ItemNewsLayoutBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val tvAuthor = itemView.tvAuthor
        val tvTitle = itemView.tvTitle
        val tvContent = itemView.tvContent
        val tvUrlNews = itemView.tvUrlNews
        val tvPublishedAt = itemView.tvPublishedDate
        val tvImageNews = itemView.ivImageNews
        val tvDescription = itemView.tvDescription

        fun bind(articles: Articles) {
            tvTitle.text = articles.title
            tvAuthor.text = articles.author
            tvContent.text = articles.content
            tvUrlNews.text = articles.url
            tvPublishedAt.text = articles.publishedAt
            tvDescription.text = articles.description
            Glide.with(itemView.context)
                .load(articles.urlToImage)
                .fitCenter()
                .into(tvImageNews)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsRecyclerViewHolder {
        val view = ItemNewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsRecyclerViewHolder, position: Int) {
        holder.bind(news[position])

        holder.itemView.setOnClickListener {
            it.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(news[position].url)))
        }
    }

    override fun getItemCount(): Int {
        return news.size
    }

    fun setData(list: List<Articles>) {
        news.clear()
        news.addAll(list)
        notifyDataSetChanged()
    }

}