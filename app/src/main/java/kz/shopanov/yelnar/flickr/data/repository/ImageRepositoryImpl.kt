package kz.shopanov.yelnar.flickr.data.repository

import javax.inject.Inject
import kz.shopanov.yelnar.flickr.data.dataSource.ImageRemoteDataSource
import kz.shopanov.yelnar.flickr.domain.model.Image

class ImageRepositoryImpl @Inject constructor(
    private val imageRemoteDataSource: ImageRemoteDataSource
) : ImageRepository {

    override suspend fun getImages(): List<Image> {
        imageRemoteDataSource.
    }
}