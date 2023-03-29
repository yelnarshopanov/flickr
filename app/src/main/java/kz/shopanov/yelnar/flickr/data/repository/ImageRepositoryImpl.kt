package kz.shopanov.yelnar.flickr.data.repository

import kz.shopanov.yelnar.flickr.data.dataSource.ImageRemoteDataSource
import kz.shopanov.yelnar.flickr.domain.model.Image
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageRemoteDataSource: ImageRemoteDataSource
) : ImageRepository {

    override suspend fun getImages(): List<Image> =
        imageRemoteDataSource.getImages().map { response ->
            Image(
                url = response.photo
            )
        }
}