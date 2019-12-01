package com.rex.mvvm_kotlin_demo.base

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import butterknife.ButterKnife

/**
author: rexkell
date: 2019/11/30
explain:
 */
public abstract class BaseActivity<T> : AppCompatActivity(),LifecycleOwner{
    protected var mContext:Activity?=null
    protected var dataBind:T?=null
    var lifecycleRegistry:LifecycleRegistry?=null
    protected abstract fun setLayout(): Int
    protected abstract fun init()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext=this
        dataBind= DataBindingUtil.setContentView<ViewDataBinding>(this,setLayout()) as T
        lifecycleRegistry= LifecycleRegistry(this)
        lifecycleRegistry!!.currentState=Lifecycle.State.CREATED
        lifecycleRegistry!!.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        init()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry!!.currentState=Lifecycle.State.STARTED
        lifecycleRegistry!!.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    override fun onStop() {
        super.onStop()
        lifecycleRegistry!!.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleRegistry!!.currentState=Lifecycle.State.DESTROYED
        lifecycleRegistry!!.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    override fun getLifecycle(): Lifecycle {
        if (lifecycleRegistry!=null){
            return lifecycleRegistry!!
        }
        return super.getLifecycle()
    }
}