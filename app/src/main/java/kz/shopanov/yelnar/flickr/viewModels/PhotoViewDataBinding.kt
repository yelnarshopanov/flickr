package kz.shopanov.yelnar.flickr.viewModels

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kz.shopanov.yelnar.flickr.application.Constants
import kz.shopanov.yelnar.flickr.views.PhotoViewActivity


/**
 * Created by Yelnar Shopanov
 * on 13.07.2018.
 */

class PhotoViewDataBinding(activity: PhotoViewActivity) {

    val imageUrl: String = activity.intent.getStringExtra(Constants.EXTRA_URL)
}

@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, url: String) {
    Picasso.get().load(url).into(view)
}