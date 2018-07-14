package com.androidhuman.example.simplegithub.ui.repo

import android.os.Bundle
import android.view.View
import com.androidhuman.example.simplegithub.R
import com.androidhuman.example.simplegithub.data.model.GithubRepo
import com.androidhuman.example.simplegithub.databinding.ActivityRepositoryBinding
import com.androidhuman.example.simplegithub.extensions.gone
import com.androidhuman.example.simplegithub.extensions.visible
import com.androidhuman.example.simplegithub.ui.base.BaseActivity
import com.androidhuman.example.simplegithub.util.State
import com.bumptech.glide.Glide
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class RepositoryActivity:
        BaseActivity<ActivityRepositoryBinding, RepositoryViewModel>(R.layout.activity_repository) {

    private val dateFormatInResponse = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault())

    private val dateFormatToShow = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val login = intent.getStringExtra(KEY_USER_LOGIN)
                ?: throw IllegalArgumentException("No login info exists in extras")

        val repo = intent.getStringExtra(KEY_REPO_NAME)
                ?: throw IllegalArgumentException("No repo info exists in extras")

        viewModel.requestRepositoryInfo(login, repo)

        viewModel.state.observe(this, android.arch.lifecycle.Observer {
            when(it) {
                is State.Init -> { hideProgress() }
                is State.Loading -> { showProgress() }
                is State.Success -> { bindRepository(it.data) }
                is State.Error -> { showError(it.message) }
            }
        })
    }

    private fun bindRepository(repository: GithubRepo) {
        with(mBinding) {
            Glide.with(this@RepositoryActivity)
                    .load(repository.owner.avatarUrl)
                    .into(ivActivityRepositoryProfile)

            tvActivityRepositoryName.text = repository.fullName
            tvActivityRepositoryStars.text = resources.getQuantityString(R.plurals.star, repository.stars, repository.stars)

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
    }

    private fun showProgress() = mBinding.pbActivityRepository.visible()

    private fun hideProgress() = mBinding.pbActivityRepository.gone()

    private fun showError(message: String)
        = with(mBinding.tvActivityRepositoryMessage) {
            text = message
            visibility = View.VISIBLE
        }

    companion object {

        const val KEY_USER_LOGIN = "user_login"

        const val KEY_REPO_NAME = "repo_name"
    }
}
