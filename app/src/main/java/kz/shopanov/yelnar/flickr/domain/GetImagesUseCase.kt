package kz.shopanov.yelnar.flickr.domain

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kz.shopanov.yelnar.flickr.common.base.UseCase
import kz.shopanov.yelnar.flickr.data.repository.ImageRepository
import kz.shopanov.yelnar.flickr.domain.model.Image

class GetImagesUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) : UseCase<Unit, List<Image>>() {

    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun execute(parameters: Unit): List<Image> = imageRepository.getImages()
}