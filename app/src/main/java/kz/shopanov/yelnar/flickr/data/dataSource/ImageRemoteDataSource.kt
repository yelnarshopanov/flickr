package kz.shopanov.yelnar.flickr.data.dataSource

import kz.shopanov.yelnar.flickr.domain.model.Image

interface ImageRemoteDataSource {

    override suspend fun getImages(): List<Image>
}