package kz.shopanov.yelnar.flickr.domain.gateway

import kz.shopanov.yelnar.flickr.domain.model.Image

interface ImageRemoteGateway {

    suspend fun getImages(): List<Image>
}