package com.rex.mvvm_kotlin_demo.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rex.mvvm_kotlin_demo.LoginInfo
import java.util.*

/**
author: rexkell
date: 2019/12/1
explain:
 带参数传递的viewModel
 */
class LoginModelForParams(info:LoginInfo?) : ViewModel(){
    var loginReturnInfo: MutableLiveData<String>?= MutableLiveData()
    var loginInfo:LoginInfo?=null
    init {
        loginInfo=info
    }
    class Factory(value:LoginInfo):ViewModelProvider.Factory{
        var mValue:LoginInfo?=null
        init {
            mValue=value
        }
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginModelForParams(mValue!!) as T
        }
        fun getValue():LoginInfo{
            return mValue!!
        }

    }

    fun login(){
        val task: TimerTask = object : TimerTask() {
            override fun run() {
                if (loginInfo!!.loginName.get().equals("1")){
                    loginReturnInfo!!.postValue("成功")
                }else{
                    loginReturnInfo!!.postValue("失败")
                }
            }
        }
        var timer= Timer()
        timer.schedule(task,1000)
    }


}