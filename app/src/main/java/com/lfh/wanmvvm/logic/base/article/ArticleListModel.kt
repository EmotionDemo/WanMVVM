package com.lfh.wanmvvm.logic.base.article

import android.text.Html
import android.text.TextUtils
import com.lfh.wanmvvm.logic.home.DataX

data class ArticleListModel(
    var id: Int = 0,
    var author: String? = null,
    var collect: Boolean = false,
    var desc: String? = null,
    var link: String? = null,
    var data: String? = null,
    var title: String? = null,
    var articleTag: String? = null,
) {
    companion object {
        //将服务器返回
        fun conToLocalData(list: List<DataX>): MutableList<ArticleListModel> {
            return list.map {
                ArticleListModel().apply {
                    id = it.id
                    author = if (!TextUtils.isEmpty(it.author)) {
                        it.author
                    } else {
                        it.shareUser
                    }
                    data = if (!TextUtils.isEmpty(it.niceDate)) {
                        it.niceDate
                    } else {
                        it.niceShareDate
                    }
                    articleTag = it.superChapterName
                    title = Html.fromHtml(it.title).toString()
                    collect = it.collect
                    desc = it.desc
                }
            }.toMutableList()
        }
    }
}