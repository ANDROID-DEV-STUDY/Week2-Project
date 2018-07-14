package com.androidhuman.example.simplegithub.ui.signin

import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import com.androidhuman.example.simplegithub.BuildConfig
import com.androidhuman.example.simplegithub.R
import com.androidhuman.example.simplegithub.databinding.ActivitySignInBinding
import com.androidhuman.example.simplegithub.extensions.gone
import com.androidhuman.example.simplegithub.extensions.onClick
import com.androidhuman.example.simplegithub.extensions.visible
import com.androidhuman.example.simplegithub.ui.base.BaseActivity
import com.androidhuman.example.simplegithub.ui.main.MainActivity
import com.androidhuman.example.simplegithub.util.State
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.longToast
import org.jetbrains.anko.newTask

class SignInActivity:
        BaseActivity<ActivitySignInBinding, SignInViewModel>(R.layout.activity_sign_in) {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.btnActivitySignInStart.onClick {
            val authUri = Uri.Builder().scheme("https").authority("github.com")
                    .appendPath("login")
                    .appendPath("oauth")
                    .appendPath("authorize")
                    .appendQueryParameter("client_id", BuildConfig.GITHUB_CLIENT_ID)
                    .build()

            val intent = CustomTabsIntent.Builder().build()
            intent.launchUrl(this@SignInActivity, authUri)
        }

        viewModel.state.observe(this, Observer {
            when(it) {
                is State.Init -> { hideProgress() }
                is State.Loading -> { showProgress() }
                is State.Success -> { if(it.data.isNotBlank()) launchMainActivity() }
                is State.Error -> { showError(it.message) }
            }
        })

        viewModel.loadAccessToken()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        val code = intent.data?.getQueryParameter("code")
                ?: throw IllegalStateException("No code exists")

        getAccessToken(code)
    }

    private fun getAccessToken(code: String)
            = viewModel.requestAccessToken(BuildConfig.GITHUB_CLIENT_ID, BuildConfig.GITHUB_CLIENT_SECRET, code)


    private fun showProgress() {
        mBinding.btnActivitySignInStart.gone()
        mBinding.pbActivitySignIn.visible()
    }

    private fun hideProgress() {
        mBinding.btnActivitySignInStart.visible()
        mBinding.pbActivitySignIn.gone()
    }

    private fun showError(message: String) {
        longToast(message)
    }

    private fun launchMainActivity() {
        startActivity(intentFor<MainActivity>().clearTask().newTask())
    }
}
