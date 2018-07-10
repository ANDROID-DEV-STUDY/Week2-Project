package com.androidhuman.example.simplegithub

import com.androidhuman.example.simplegithub.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class SimpleGithubApp: DaggerApplication() {
    // DaggerAppComponent 의 인스턴스를 반환
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build() // DaggerAppComponent 는 프로젝트를 빌드해야 생성된다.
    } // 뭐하는 걸까.... ㅎㅎ

}