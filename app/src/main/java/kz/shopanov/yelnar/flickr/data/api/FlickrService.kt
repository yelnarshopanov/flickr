package kz.shopanov.yelnar.flickr.data.api

import kz.shopanov.yelnar.flickr.data.model.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrService {

    @GET("services/rest/?method=flickr.photos.search&nojsoncallback=1&format=json")
    suspend fun search(
        @Query("api_key") apiKey: String,
        @Query("text") text: String,
        @Query("extras") extras: String = "url_s, url_o"
    ): List<ImageResponse>
}