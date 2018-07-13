package kz.shopanov.yelnar.flickr.views

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.*
import kz.shopanov.yelnar.flickr.R
import kz.shopanov.yelnar.flickr.databinding.ActivityMainBinding
import kz.shopanov.yelnar.flickr.viewModels.SearchDataBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = SearchDataBinding(this)
        binding.executePendingBindings()

        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        binding.viewModel?.setupSearchView(menu.findItem(R.id.action_search).actionView as SearchView)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onNewIntent(intent: Intent) {
        binding.viewModel?.activityOnNewIntent(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewModel?.activityOnDestroy()
    }
}