package kz.shopanov.yelnar.flickr.presentation.ui.imagelist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import kz.shopanov.yelnar.flickr.R
import kz.shopanov.yelnar.flickr.common.base.BaseListAdapter
import kz.shopanov.yelnar.flickr.common.base.BaseViewHolder
import kz.shopanov.yelnar.flickr.common.utils.setThrottleOnClickListener
import kz.shopanov.yelnar.flickr.databinding.ItemImageBinding

class ImageAdapter : BaseListAdapter<ImageItem>(ImageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ImageItem> =
        ImageViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    private class ImageViewHolder(
        private val viewBinding: ItemImageBinding
    ) : BaseViewHolder<ImageItem>(viewBinding.root) {

        override fun onBind(item: ImageItem): Unit = with(viewBinding) {
            updateImage(item)

            itemView.setThrottleOnClickListener { item.onItemClicked() }
        }

        override fun update(item: ImageItem, keys: Set<String>) {
            keys.forEach { key ->
                when (key) {
                    DIFF_FIELD_IMAGE_URL -> updateImage(item)
                }
            }
        }

        private fun updateImage(item: ImageItem) = with(viewBinding) {
            Glide.with(ivImage)
                .load(item.image.url)
                .placeholder(R.drawable.animated_image_placeholder)
                .into(ivImage)
        }
    }

    private class ImageDiffCallback : DiffUtil.ItemCallback<ImageItem>() {

        override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean =
            oldItem.image.url == newItem.image.url

        override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean =
            oldItem.image == newItem.image

        override fun getChangePayload(oldItem: ImageItem, newItem: ImageItem): Any? {
            val diff = mutableSetOf<String>()

            if (oldItem.image.url != newItem.image.url) diff.add(DIFF_FIELD_IMAGE_URL)

            return diff.takeIf { it.isNotEmpty() }
        }
    }

    companion object {

        private const val DIFF_FIELD_IMAGE_URL = "DIFF_FIELD_IMAGE_URL"
    }
}