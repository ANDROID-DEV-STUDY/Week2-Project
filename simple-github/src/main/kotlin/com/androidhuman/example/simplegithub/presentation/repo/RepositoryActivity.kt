package com.androidhuman.example.simplegithub.presentation.repo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.androidhuman.example.simplegithub.R
import com.androidhuman.example.simplegithub.data.remote.api.GithubApi
import com.androidhuman.example.simplegithub.entity.GithubRepo
import com.androidhuman.example.simplegithub.util.extensions.plusAssign
import com.androidhuman.example.simplegithub.util.rx.AutoClearedDisposable
import com.androidhuman.example.simplegithub.presentation.GlideApp
import com.androidhuman.example.simplegithub.util.State
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_repository.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class RepositoryActivity : DaggerAppCompatActivity() {

    companion object {

        const val KEY_USER_LOGIN = "user_login"

        const val KEY_REPO_NAME = "repo_name"
    }

    @Inject lateinit var viewModelFactory: RepositoryViewModelFactory

    lateinit var viewModel: RepositoryViewModel

    internal val dateFormatInResponse = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault())

    internal val dateFormatToShow = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    @Inject lateinit var githubApi: GithubApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)


        val login = intent.getStringExtra(KEY_USER_LOGIN) ?: throw IllegalArgumentException(
                "No login info exists in extras")
        val repo = intent.getStringExtra(KEY_REPO_NAME) ?: throw IllegalArgumentException(
                "No repo info exists in extras")

        initViewModel()
        viewModel.requestRepositoryInfo(login, repo)

    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(
                this, viewModelFactory)[RepositoryViewModel::class.java]

        viewModel.state.observe(this, Observer {
            when(it) {
                is State.Loading -> showProgress()
                is State.Success -> {
                    hideProgress()
                    setRepoToView(it.data)
                    setContentVisibility(true)
                }
                is State.Error -> {
                    hideProgress()
                    setContentVisibility(false)
                    showError(it.message)
                }
            }
        })
    }

    private fun setRepoToView(repository: GithubRepo) {
        GlideApp.with(this@RepositoryActivity)
                .load(repository.owner.avatarUrl)
                .into(ivActivityRepositoryProfile)

        tvActivityRepositoryName.text = repository.fullName
        tvActivityRepositoryStars.text = resources
                .getQuantityString(R.plurals.star, repository.stars, repository.stars)
        if (null == repository.description) {
            tvActivityRepositoryDescription.setText(R.string.no_description_provided)
        } else {
            tvActivityRepositoryDescription.text = repository.description
        }
        if (null == repository.language) {
            tvActivityRepositoryLanguage.setText(R.string.no_language_specified)
        } else {
            tvActivityRepositoryLanguage.text = repository.language
        }

        try {
            val lastUpdate = dateFormatInResponse.parse(repository.updatedAt)
            tvActivityRepositoryLastUpdate.text = dateFormatToShow.format(lastUpdate)
        } catch (e: ParseException) {
            tvActivityRepositoryLastUpdate.text = getString(R.string.unknown)
        }
    }

    private fun showProgress() {
        pbActivityRepository.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        pbActivityRepository.visibility = View.GONE
    }

    private fun setContentVisibility(show: Boolean) {
        grpActivityRepositoryContent.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(message: String) {
        with(tvActivityRepositoryMessage) {
            text = message
            visibility = View.VISIBLE
        }
    }
}
