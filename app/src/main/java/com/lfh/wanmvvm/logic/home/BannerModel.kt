package com.lfh.wanmvvm.logic.home

data class BannerModel(
    var desc: String? = null,
    var id: Int = 0,
    var imagePath: String?,
    var isVisible: Int = 0,
    var order: Int = 0,
    var title: String? = null,
    var type: Int = 0,
    var url: String? = null
)