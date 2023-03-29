package kz.shopanov.yelnar.flickr.data.api

import javax.inject.Inject
import kz.shopanov.yelnar.flickr.data.dataSource.ImageRemoteDataSource
import kz.shopanov.yelnar.flickr.data.model.ImageResponse

class FlickrRestAPI @Inject constructor(
    private val service: FlickrService
) : ImageRemoteDataSource {

    override suspend fun getImages(): List<ImageResponse> =
        service.search(apiKey = "572c9e7b62d487f099d2dea2db545ae0", text = "a")
}