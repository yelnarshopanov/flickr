package kz.shopanov.yelnar.flickr.flickrApi

import io.reactivex.Single
import kz.shopanov.yelnar.flickr.entities.PhotosResponseEntity
import retrofit2.adapter.rxjava2.Result

/**
 * Created by Yelnar Shopanov
 * on 12.07.2018.
 */

class SearchRepository(private val apiService: FlickrApiService) {

    fun searchPhotos(apiKey: String, query: String): Single<PhotosResponseEntity> {
        return apiService.search(apiKey, query)
    }
}