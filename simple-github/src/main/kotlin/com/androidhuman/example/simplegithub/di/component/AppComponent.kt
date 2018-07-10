package com.androidhuman.example.simplegithub.di.component

import android.app.Application
import com.androidhuman.example.simplegithub.di.ActivityBinder
import com.androidhuman.example.simplegithub.SimpleGithubApp
import com.androidhuman.example.simplegithub.di.module.ApiModule
import com.androidhuman.example.simplegithub.di.module.AppModule
import com.androidhuman.example.simplegithub.di.module.LocalDataModule
import com.androidhuman.example.simplegithub.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

// 대거의 안드로이드 지원 모듈인 AndroidSupportInjectionModule 을 추가해야 한다.
// 위를 사용하기 위해 AndroidInjector 인터페이스를 상속(SimpleGithubApp 을 타입 인자로 넣는다.)
// 객체 그래프에 추가하기 위해 @Component.Builder 인터페이스 내에 이를 위한 코드 추가
@Singleton
@Component(modules = [
    AppModule::class,
    LocalDataModule::class,
    ApiModule::class,
    NetworkModule::class,
    AndroidSupportInjectionModule::class,
    ActivityBinder::class
])
interface AppComponent : AndroidInjector<SimpleGithubApp> { // Scope = Application

    @Component.Builder // AppComponent 생성 시 사용할 빌더 클래스 정의
    interface Builder {
        @BindsInstance // 객체 그래프에 추가할 객체를 선언 (객체 그래프에 추가할 객체를 인자로 받고, 빌더 클래스를 반환하는 함수 형태로 선언)
        fun application(app:Application) : Builder // 여기서는 Application 객체를 객체 그래프에 포함하도록 설정

        // 빌더 클래스는 컴포넌트를 반환하는 build() 함수를 반드시 포함해야 함
        fun build(): AppComponent
    }
}