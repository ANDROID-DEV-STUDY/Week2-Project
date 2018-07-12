package com.androidhuman.example.simplegithub.ui.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.androidhuman.example.simplegithub.R
import com.androidhuman.example.simplegithub.data.model.GithubRepo
import com.androidhuman.example.simplegithub.databinding.ActivityMainBinding
import com.androidhuman.example.simplegithub.extensions.plusAssign
import com.androidhuman.example.simplegithub.rx.AutoActivatedDisposable
import com.androidhuman.example.simplegithub.rx.AutoClearedDisposable
import com.androidhuman.example.simplegithub.ui.base.BaseActivity
import com.androidhuman.example.simplegithub.ui.repo.RepositoryActivity
import com.androidhuman.example.simplegithub.ui.search.SearchActivity
import com.androidhuman.example.simplegithub.ui.search.SearchAdapter
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.startActivity

class MainActivity :
        BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main),
        SearchAdapter.ItemClickListener {

    override val modelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    internal val adapter by lazy {
        SearchAdapter().apply { setItemClickListener(this@MainActivity) }
    }

    internal val disposables = AutoClearedDisposable(this)

    internal val viewDisposables
            = AutoClearedDisposable(lifecycleOwner = this, alwaysClearOnStop = false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle += disposables
        lifecycle += viewDisposables
        lifecycle += AutoActivatedDisposable(this) {
            viewModel.searchHistory
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { items ->
                        with(adapter) {
                            if (items.isEmpty) {
                                clearItems()
                            } else {
                                setItems(items.value)
                            }
                            notifyDataSetChanged()
                        }
                    }
        }

        mBinding.btnActivityMainSearch.setOnClickListener {
            startActivity<SearchActivity>()
        }

        with(mBinding.rvActivityMainList) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        viewDisposables += viewModel.message
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { message ->
                    if (message.isEmpty) {
                        hideMessage()
                    } else {
                        showMessage(message.value)
                    }
                }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (R.id.menu_activity_main_clear_all == item.itemId) {
            disposables += viewModel.clearSearchHistory()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(repository: GithubRepo) {
        startActivity<RepositoryActivity>(
                RepositoryActivity.KEY_USER_LOGIN to repository.owner.login,
                RepositoryActivity.KEY_REPO_NAME to repository.name)
    }

    private fun showMessage(message: String) {
        with(mBinding.tvActivityMainMessage) {
            text = message
            visibility = View.VISIBLE
        }
    }

    private fun hideMessage() {
        with(mBinding.tvActivityMainMessage) {
            text = ""
            visibility = View.GONE
        }
    }
}
