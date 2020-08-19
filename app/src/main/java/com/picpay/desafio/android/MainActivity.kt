package com.picpay.desafio.android

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewModel by viewModel<MainViewModel>()
    private val adapter = UserListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpList()
        getUsers()
        onReceiveEvents()
    }

    private fun setUpList() {
        recyclerView.adapter = adapter
    }

    private fun getUsers() = with(viewModel) {
        launch {
            getUsers()
        }
    }

    private fun onReceiveEvents() = with(viewModel) {
        usersLv.observe(this@MainActivity, Observer { users ->
            adapter.users = users
        })
        progressLv.observe(this@MainActivity, Observer { show ->
            if (show) {
                progress_bar.visibility = View.VISIBLE
            } else {
                progress_bar.visibility = View.GONE
            }
        })
        showErrorLv.observe(this@MainActivity, Observer {
            recyclerView.visibility = View.GONE
            Toast.makeText(this@MainActivity, getString(R.string.error), Toast.LENGTH_SHORT)
                .show()
        })
    }
}
