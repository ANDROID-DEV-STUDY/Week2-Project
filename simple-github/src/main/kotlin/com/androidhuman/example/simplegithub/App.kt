package com.androidhuman.example.simplegithub

import android.app.Application
import com.androidhuman.example.simplegithub.di.component.AppComponent
import com.androidhuman.example.simplegithub.di.component.DaggerAppComponent
import com.androidhuman.example.simplegithub.di.module.ApiModule
import com.androidhuman.example.simplegithub.di.module.AppModule
import com.androidhuman.example.simplegithub.di.module.DatabaseModule
import com.androidhuman.example.simplegithub.ui.main.MainActivity
import com.androidhuman.example.simplegithub.ui.main.MainComponent
import com.androidhuman.example.simplegithub.ui.main.MainModule
import com.androidhuman.example.simplegithub.ui.repo.RepositoryActivity
import com.androidhuman.example.simplegithub.ui.repo.RepositoryComponent
import com.androidhuman.example.simplegithub.ui.repo.RepositoryModule
import com.androidhuman.example.simplegithub.ui.search.SearchActivity
import com.androidhuman.example.simplegithub.ui.search.SearchComponent
import com.androidhuman.example.simplegithub.ui.search.SearchModule
import com.androidhuman.example.simplegithub.ui.signin.SignInActivity
import com.androidhuman.example.simplegithub.ui.signin.SignInComponent
import com.androidhuman.example.simplegithub.ui.signin.SignInModule

class App : Application() {

    lateinit var appComponent : AppComponent

    lateinit var mainComponent : MainComponent

    lateinit var repositoryComponent: RepositoryComponent

    lateinit var searchComponent: SearchComponent

    lateinit var signInComponent: SignInComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .apiModule(ApiModule())
                .databaseModule(DatabaseModule())
                .build()
    }

    fun createMainComponent(mainActivity: MainActivity) : MainComponent {
        mainComponent = appComponent.plus(MainModule(mainActivity))
        return mainComponent
    }

    fun createRepositoryComponent(repositoryActivity: RepositoryActivity) : RepositoryComponent {
        repositoryComponent = appComponent.plus(RepositoryModule(repositoryActivity))
        return repositoryComponent
    }

    fun createSearchComponent(searchActivity: SearchActivity) : SearchComponent {
        searchComponent = appComponent.plus(SearchModule(searchActivity))
        return searchComponent
    }

    fun createSignInComponent(signInActivity: SignInActivity) : SignInComponent {
        signInComponent = appComponent.plus(SignInModule(signInActivity))
        return signInComponent
    }
}