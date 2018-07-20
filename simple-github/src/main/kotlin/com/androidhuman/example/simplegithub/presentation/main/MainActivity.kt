package com.androidhuman.example.simplegithub.presentation.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.androidhuman.example.simplegithub.R
import com.androidhuman.example.simplegithub.entity.GithubRepo
import com.androidhuman.example.simplegithub.presentation.repo.RepositoryActivity
import com.androidhuman.example.simplegithub.presentation.search.SearchActivity
import com.androidhuman.example.simplegithub.presentation.search.SearchAdapter
import com.androidhuman.example.simplegithub.util.State
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), SearchAdapter.ItemClickListener {

    @Inject lateinit var adapter: SearchAdapter

    @Inject lateinit var viewModelFactory: MainViewModelFactory

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        build()

    }

    private fun build() {
        initViewModel()
        initRecyclerView()

        btnActivityMainSearch.setOnClickListener {
            startActivity<SearchActivity>()
        }

        viewModel.searchHistory()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(
                this, viewModelFactory)[MainViewModel::class.java]

        viewModel.stateGetHistory.observe(this, Observer {
            when (it) {
                is State.Loading -> { }
                is State.Success -> {
                    hideMessage()
                    adapter.apply {
                        setItems(it.data)
                        notifyDataSetChanged()
                    }
                }
                is State.Error -> {
                    showMessage(it.message)
                    adapter.clearItems()
                }
            }
        })

        viewModel.stateClearHistory.observe(this, Observer {
            when (it) {
                is State.Loading -> { }
                is State.Success -> {
                    hideMessage()
                    adapter.apply {
                        clearItems()
                        notifyDataSetChanged()
                    }
                }
                is State.Error -> {
                    showMessage(it.message)
                }
            }
        })
    }

    private fun initRecyclerView() {
        with(rvActivityMainList) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
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
        with(tvActivityMainMessage) {
            text = message
            visibility = View.VISIBLE
        }
    }

    private fun hideMessage() {
        with(tvActivityMainMessage) {
            text = ""
            visibility = View.GONE
        }
    }
}
