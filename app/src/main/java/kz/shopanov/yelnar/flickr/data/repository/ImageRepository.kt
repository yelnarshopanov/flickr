package kz.shopanov.yelnar.flickr.data.repository

import kz.shopanov.yelnar.flickr.domain.model.Image

interface ImageRepository {

    suspend fun getImages(): List<Image>
}