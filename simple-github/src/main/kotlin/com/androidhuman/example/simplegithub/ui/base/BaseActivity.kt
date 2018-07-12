package com.androidhuman.example.simplegithub.ui.base

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.androidhuman.example.simplegithub.extensions.plusAssign
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel> (
        @LayoutRes private val layoutResID: Int
): AppCompatActivity() {

    lateinit var mBinding : DB

    @Inject lateinit var viewModelFactory : ViewModelProvider.Factory

    lateinit var viewModel : VM

    abstract val modelClass : Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, layoutResID)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[modelClass]

        lifecycle += viewModel
    }
}