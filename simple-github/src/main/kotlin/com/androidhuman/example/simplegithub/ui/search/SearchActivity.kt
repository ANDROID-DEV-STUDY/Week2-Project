package com.androidhuman.example.simplegithub.ui.search

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.androidhuman.example.simplegithub.R
import com.androidhuman.example.simplegithub.data.model.GithubRepo
import com.androidhuman.example.simplegithub.databinding.ActivitySearchBinding
import com.androidhuman.example.simplegithub.extensions.gone
import com.androidhuman.example.simplegithub.extensions.plusAssign
import com.androidhuman.example.simplegithub.extensions.visible
import com.androidhuman.example.simplegithub.rx.AutoClearedDisposable
import com.androidhuman.example.simplegithub.ui.base.BaseActivity
import com.androidhuman.example.simplegithub.ui.repo.RepositoryActivity
import com.androidhuman.example.simplegithub.util.State
import com.jakewharton.rxbinding2.support.v7.widget.queryTextChangeEvents
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SearchActivity:
        BaseActivity<ActivitySearchBinding, SearchViewModel>(R.layout.activity_search),
        SearchAdapter.ItemClickListener {

    private lateinit var menuSearch: MenuItem

    private lateinit var searchView: SearchView

    @Inject lateinit var adapter: SearchAdapter

    private val viewDisposables
            = AutoClearedDisposable(lifecycleOwner = this, alwaysClearOnStop = false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(mBinding.rvActivitySearchList) {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = this@SearchActivity.adapter
        }

        viewModel.state.observe(this, Observer {
            when(it) {
                is State.Init -> {
                    hideProgress()
                    hideError()
                }
                is State.Loading -> { showProgress() }
                is State.Success -> {  }
                is State.Error -> {
                    bindRepository(emptyList())
                    showError(it.message)
                }
            }
        })
    }

    private fun bindRepository(repos: List<GithubRepo>)
        = with(adapter) {
            if (repos.isEmpty()) {
                clearItems()
            } else {
                setItems(repos)
            }
            notifyDataSetChanged()
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_search, menu)

        menuSearch = menu.findItem(R.id.menu_activity_search_query)
        searchView = (menuSearch.actionView as SearchView)

        viewDisposables += searchView.queryTextChangeEvents()
                .filter { it.isSubmitted }
                .map { it.queryText() }
                .filter { it.isNotEmpty() }
                .map { it.toString() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { query ->
                    updateTitle(query)
                    hideSoftKeyboard()
                    collapseSearchView()
                    searchRepository(query)
                }

        viewModel.lastSearchKeyword.observe(this, Observer {
            it?.takeIf { it.isNotBlank() }
                    ?.let { updateTitle(it) }
                    ?: menuSearch.expandActionView()
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (R.id.menu_activity_search_query == item.itemId) {
            item.expandActionView()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(repository: GithubRepo) {
        viewModel.addToSearchHistory(repository)

        startActivity<RepositoryActivity>(
                RepositoryActivity.KEY_USER_LOGIN to repository.owner.login,
                RepositoryActivity.KEY_REPO_NAME to repository.name)
    }

    private fun searchRepository(query: String) = viewModel.searchRepository(query)

    private fun updateTitle(query: String) = supportActionBar?.run { subtitle = query }

    private fun hideSoftKeyboard() =
            (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
                    .run { hideSoftInputFromWindow(searchView.windowToken, 0) }

    private fun collapseSearchView() = menuSearch.collapseActionView()

    private fun showProgress() = mBinding.pbActivitySearch.visible()

    private fun hideProgress() = mBinding.pbActivitySearch.gone()

    private fun showError(message: String?) {
        with(mBinding.tvActivitySearchMessage) {
            text = message ?: "Unexpected error."
            visibility = View.VISIBLE
        }
    }

    private fun hideError() {
        with(mBinding.tvActivitySearchMessage) {
            text = ""
            visibility = View.GONE
        }
    }
}
