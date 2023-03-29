package kz.shopanov.yelnar.flickr.data.dataSource

import kz.shopanov.yelnar.flickr.data.model.ImageResponse

interface ImageRemoteDataSource {

    suspend fun getImages(): List<ImageResponse>
}