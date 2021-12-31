package com.lfh.wanmvvm.logic.base

import com.lfh.wanmvvm.exception.ApiException


/**
 * desc: sealed修饰的类 在kotlin中是密封类 密封类 描述的是 父类和子类的关系
1、密封类和它的子类必须定义在一个文件中，而在kotlin1.0的时候 密封类的子类必须定义在密封类 里面
2、Sealed types cannot be instantiated 密封类是不能被初始化的
如何理解 这个父类和子类的关系呢？
可以这样理解 父类 只是一个组织者（对于子类来说）（除了这个功能它什么也做不了）甚至初使化都做不到，
具体可以出面做事情的 是子 类

 * @author :lifenghua
 * createData :2021/12/30 22:34
 */
sealed class ResultData<out T> {
    data class Success<T>(val response: T) : ResultData<T>()
    data class Error<T>(val exception: ApiException) : ResultData<Nothing>()
}