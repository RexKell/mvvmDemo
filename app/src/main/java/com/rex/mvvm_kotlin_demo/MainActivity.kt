package com.rex.mvvm_kotlin_demo

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rex.mvvm_kotlin_demo.base.BaseActivity
import com.rex.mvvm_kotlin_demo.databinding.ActivityMainBinding
import com.rex.mvvm_kotlin_demo.viewModel.LoginModelForParams
import com.rex.mvvm_kotlin_demo.viewModel.LoginViewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    var loginViewModel:LoginViewModel?=null
    var loginModelForParams:LoginModelForParams?=null
    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        loginViewModel= ViewModelProviders.of(this).get(LoginViewModel::class.java)
        (dataBind as ActivityMainBinding).loginInfo=LoginInfo()
        var info:LoginInfo?=(dataBind as ActivityMainBinding).loginInfo
        loginModelForParams=ViewModelProviders.of(this,LoginModelForParams.Factory(info!!)).get(LoginModelForParams::class.java)
        (dataBind as ActivityMainBinding).btnLogin.setOnClickListener(OnClickListener { v:View? ->
            Toast.makeText(mContext,"点击了",Toast.LENGTH_SHORT).show()
            loginViewModel!!.login((dataBind as ActivityMainBinding).loginInfo!!)
         })
        //带参数的viewModel
        (dataBind as ActivityMainBinding).btnLogin1.setOnClickListener(OnClickListener { v:View? ->
            Toast.makeText(mContext,"点击了",Toast.LENGTH_SHORT).show()
            loginModelForParams!!.login()
        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel!!.loginReturnInfo!!.observe(this, Observer<String> {
            Toast.makeText(mContext,it,Toast.LENGTH_SHORT).show()
        })
        loginModelForParams!!.loginReturnInfo!!.observe(this, Observer{
            Toast.makeText(mContext,it,Toast.LENGTH_SHORT).show()
        })
    }
}
