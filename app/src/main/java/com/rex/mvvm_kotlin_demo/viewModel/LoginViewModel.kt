package com.rex.mvvm_kotlin_demo.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rex.mvvm_kotlin_demo.LoginInfo
import java.util.*


/**
author: rexkell
date: 2019/11/30
explain:
 */
class LoginViewModel:ViewModel(){
    var loginReturnInfo:MutableLiveData<String>?=MutableLiveData()
    fun login(loginInfo:LoginInfo){
       val task: TimerTask = object : TimerTask() {
           override fun run() {
               if (loginInfo.loginName.get().equals("1")){
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