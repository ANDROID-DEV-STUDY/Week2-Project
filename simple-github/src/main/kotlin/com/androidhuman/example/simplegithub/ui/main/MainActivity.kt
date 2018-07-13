package com.androidhuman.example.simplegithub.ui.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.androidhuman.example.simplegithub.R
import com.androidhuman.example.simplegithub.data.model.GithubRepo
import com.androidhuman.example.simplegithub.databinding.ActivityMainBinding
import com.androidhuman.example.simplegithub.ui.base.BaseActivity
import com.androidhuman.example.simplegithub.ui.repo.RepositoryActivity
import com.androidhuman.example.simplegithub.ui.search.SearchActivity
import com.androidhuman.example.simplegithub.ui.search.SearchAdapter
import com.androidhuman.example.simplegithub.util.State
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class MainActivity :
        BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main),
        SearchAdapter.ItemClickListener {

    override val modelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    @Inject lateinit var mAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadHistory()

        viewModel.state.observe(this, Observer {
            when(it) {
                is State.Init -> { hideMessage() }
                is State.Loading -> {  }
                is State.Success -> { mAdapter.setItems(it.data) }
                is State.Error -> { showMessage(it.message) }
            }
        })

        mBinding.btnActivityMainSearch.setOnClickListener {
            startActivity<SearchActivity>()
        }

        with(mBinding.rvActivityMainList) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter.apply { setItemClickListener(this@MainActivity) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (R.id.menu_activity_main_clear_all == item.itemId) {
            viewModel.clearSearchHistory()
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
