package com.androidhuman.example.simplegithub.presentation.signin

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.util.Log
import android.view.View
import com.androidhuman.example.simplegithub.BuildConfig
import com.androidhuman.example.simplegithub.R
import com.androidhuman.example.simplegithub.util.extensions.plusAssign
import com.androidhuman.example.simplegithub.util.rx.AutoClearedDisposable
import com.androidhuman.example.simplegithub.presentation.main.MainActivity
import com.androidhuman.example.simplegithub.util.State
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.longToast
import org.jetbrains.anko.newTask
import javax.inject.Inject


class SignInActivity : DaggerAppCompatActivity() { // 1. DaggerAppCompatActivity 변경

    @Inject lateinit var viewModelFactory: SignInViewModelFactory

    lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        build()
    }

    private fun build() {
        initViewModel()
        initButton()

        viewModel.loadAccessToken()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(
                this, viewModelFactory)[SignInViewModel::class.java]

        viewModel.state.observe(this, Observer {
            when (it) {
                is State.Loading -> {
                    showProgress()
                }
                is State.Success -> {
                    hideProgress()
                    if (it.data.isNotBlank()) launchMainActivity()
                }
                is State.Error -> {
                    hideProgress()
                    showError(it.message)
                }
            }
        })
    }

    private fun initButton() {
        btnActivitySignInStart.setOnClickListener {
            val authUri = Uri.Builder().scheme("https").authority("github.com")
                    .appendPath("login")
                    .appendPath("oauth")
                    .appendPath("authorize")
                    .appendQueryParameter("client_id", BuildConfig.GITHUB_CLIENT_ID)
                    .build()

            val intent = CustomTabsIntent.Builder().build()
            intent.launchUrl(this@SignInActivity, authUri)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        val code = intent.data?.getQueryParameter("code")
                ?: throw IllegalStateException("No code exists")

        getAccessToken(code)
    }

    private fun getAccessToken(code: String) {
        viewModel.requestAccessToken(code)
    }

    private fun showProgress() {
        btnActivitySignInStart.visibility = View.GONE
        pbActivitySignIn.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        btnActivitySignInStart.visibility = View.VISIBLE
        pbActivitySignIn.visibility = View.GONE
    }

    private fun showError(message: String) {
        longToast(message)
    }

    private fun launchMainActivity() {
        startActivity(intentFor<MainActivity>().clearTask().newTask())
    }
}
