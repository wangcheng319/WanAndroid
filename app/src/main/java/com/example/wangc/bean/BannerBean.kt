package com.example.wangc.bean

/**
 * Created by wangc on 2018/7/6
 * E-MAIL:274281610@QQ.COM
 */
//data class BannerBean(var desc:String,var id:Int,
//                      var imagePath:String,var isVisible:Int,
//                      var order:Int,var title:String,
//                      var type:Int,var url:String)

data class BannerBean(
        var errorCode: Int,
        var errorMsg: String?,
        var data: List<Data>?
) {
    data class Data(
            var id: Int,
            var url: String,
            var imagePath: String,
            var title: String,
            var desc: String,
            var isVisible: Int,
            var order: Int,
            var `type`: Int
    )
}
