package kz.shopanov.yelnar.flickr.views

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_photo_view.*
import kz.shopanov.yelnar.flickr.R
import kz.shopanov.yelnar.flickr.databinding.ActivityPhotoViewBinding
import kz.shopanov.yelnar.flickr.viewModels.PhotoViewDataBinding

class PhotoViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityPhotoViewBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_photo_view)
        binding.viewModel = PhotoViewDataBinding(this)
        binding.executePendingBindings()

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}