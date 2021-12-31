package com.lfh.wanmvvm.logic.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lfh.wanmvvm.R
import com.lfh.wanmvvm.logic.base.article.ArticleListModel

class HomeArticleAdapter(
    private val dataArticle: MutableList<ArticleListModel>,
    private val context: Context,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_ITEM_DATA = 0
        const val VIEW_ITEM_LOADER = 0
    }

    inner class DataVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    inner class LoaderVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewData =
            LayoutInflater.from(context).inflate(R.layout.layout_item_article, parent, false)


        val viewLoadMore =
            LayoutInflater.from(context).inflate(R.layout.layout_item_load_more, parent, false)
        if (viewType == VIEW_ITEM_DATA) {
            return DataVH(viewData)
        } else {
            return LoaderVH(viewLoadMore)
        }
    }


    override fun getItemCount(): Int {
        return dataArticle.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }


}