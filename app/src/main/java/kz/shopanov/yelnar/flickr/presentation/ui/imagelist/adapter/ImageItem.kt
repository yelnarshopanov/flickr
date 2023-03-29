package kz.shopanov.yelnar.flickr.presentation.ui.imagelist.adapter

import kz.shopanov.yelnar.flickr.domain.model.Image

data class ImageItem(
    val image: Image,
    val onItemClicked: () -> Unit
)