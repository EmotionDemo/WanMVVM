package com.lfh.wanmvvm.logic.login

class LoginModel {
    /**
     * data : {"admin":false,"chapterTops":[],"coinCount":851,"collectIds":[15222,15221,18453,19040,19035,17083,17131,18930,19180,19476,18414,2458,8652,18281,1165,2,20130,20087],"email":"739574055@qq.com","icon":"","id":76328,"nickname":"LFHQAQ","password":"","publicName":"LFHQAQ","token":"","type":0,"username":"17853461844"}
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
         * coinCount : 851
         * collectIds : [15222,15221,18453,19040,19035,17083,17131,18930,19180,19476,18414,2458,8652,18281,1165,2,20130,20087]
         * email : 739574055@qq.com
         * icon :
         * id : 76328
         * nickname : LFHQAQ
         * password :
         * publicName : LFHQAQ
         * token :
         * type : 0
         * username : 17853461844
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
        var collectIds: List<Int>? = null
    }
}
