package com.lfh.wanmvvm.logic.login

import com.lfh.wanmvvm.logic.base.BaseModel

class LoginModel(errorMsg: String, errorCode: Int) : BaseModel(errorMsg, errorCode) {
    var admin: Boolean = false
    var email: String? = null
    var icon: String? = null
    var id: Int = 0
    var nickname: String? = null
    var password: String? = null
    var publicName: String? = null
    var token: String? = null
    var type: Int = 0
    var username: String? = null
    var chapterTops: List<*>? = null
    var collectIds: List<Int>? = null
}
