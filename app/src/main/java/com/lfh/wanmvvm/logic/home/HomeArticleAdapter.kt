package com.lfh.wanmvvm.logic.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lfh.wanmvvm.R
import com.lfh.wanmvvm.databinding.LayoutItemArticleBinding
import com.lfh.wanmvvm.databinding.LayoutItemLoadMoreBinding

class HomeArticleAdapter(
    private val dataArticle: List<DataX>,
    private val context: Context,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_ITEM_DATA = 0
        const val VIEW_ITEM_LOADER = 1
        const val VIEW_ITEM_NOMORE = 2
        var isLoadMore = false
        var isNoMore = false
    }

    class DataVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvAuthor: TextView? = null
        internal var tvTime: TextView? = null
        internal var tvTitle: TextView? = null
        internal var tvType: TextView? = null
        internal var rlZan: RelativeLayout? = null
        internal var rlContent: RelativeLayout? = null
        internal var ivHomeZan: ImageView? = null

        init {
            tvAuthor = itemView.findViewById(R.id.tvAuthor)
            tvTime = itemView.findViewById(R.id.tvTime)
            tvTitle = itemView.findViewById(R.id.tv_artTitle)
            tvType = itemView.findViewById(R.id.tvArcType)
            rlZan = itemView.findViewById(R.id.rl_home_zan)
            rlContent = itemView.findViewById(R.id.rlContent)
            ivHomeZan = itemView.findViewById(R.id.ivHomeZan)
        }

    }

    inner class LoaderVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var ll_load_more: LinearLayout? = null

        init {
            ll_load_more = itemView.findViewById(R.id.ll_load_more)
        }
    }

    /**
     * 创建viewholder并与databinding绑定
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_ITEM_DATA) {
            val binding: LayoutItemArticleBinding =
                DataBindingUtil.inflate(LayoutInflater.from(context),
                    R.layout.layout_item_article, parent, false)
            DataVH(binding.root)
        } else {
            val binding: LayoutItemLoadMoreBinding =
                DataBindingUtil.inflate(LayoutInflater.from(context),
                    R.layout.layout_item_load_more,
                    parent,
                    false)
            LoaderVH(binding.root)
        }

    }


    override fun getItemCount(): Int {
        if (isLoadMore || isNoMore) {
            return dataArticle.size + 1
        } else {
            return dataArticle.size
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = if (holder is DataVH) {
            DataBindingUtil.getBinding<LayoutItemArticleBinding>(holder.itemView)
        } else {
            DataBindingUtil.getBinding<LayoutItemLoadMoreBinding>(holder.itemView)
        }
        binding?.executePendingBindings()
    }

    override fun getItemViewType(position: Int): Int {
        if (dataArticle.size == position + 1) {
            if (isNoMore) {
                VIEW_ITEM_LOADER
            } else if (isLoadMore) {
                VIEW_ITEM_NOMORE
            }
        } else {
            VIEW_ITEM_DATA
        }
        return 0
    }


}