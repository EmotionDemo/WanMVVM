package com.lfh.wanmvvm.logic.model

/**
 * 注册数据model
 */
class RegisterModel {
    /**
     * data : {"admin":false,"chapterTops":[],"coinCount":0,"collectIds":[],"email":"","icon":"","id":114548,"nickname":"WZB","password":"","publicName":"WZB","token":"","type":0,"username":"WZB"}
     * errorCode : 0
     * errorMsg :
     */
    var data: DataBean? = null
    var errorCode = 0
    var errorMsg: String? = null

    class DataBean {
        /**
         * admin : false
         * chapterTops : []
         * coinCount : 0
         * collectIds : []
         * email :
         * icon :
         * id : 114548
         * nickname : WZB
         * password :
         * publicName : WZB
         * token :
         * type : 0
         * username : WZB
         */
        var isAdmin = false
        var coinCount = 0
        var email: String? = null
        var icon: String? = null
        var id = 0
        var nickname: String? = null
        var password: String? = null
        var publicName: String? = null
        var token: String? = null
        var type = 0
        var username: String? = null
        var chapterTops: List<*>? = null
        var collectIds: List<*>? = null
    }
}