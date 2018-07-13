package kz.shopanov.yelnar.flickr.flickrApi

import io.reactivex.Single
import kz.shopanov.yelnar.flickr.entities.PhotosResponseEntity
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Yelnar Shopanov
 * on 12.07.2018.
 */

interface FlickrApiService {

    @GET("services/rest/?method=flickr.photos.search&nojsoncallback=1&format=json")
    fun search(
            @Query("api_key") apiKey: String,
            @Query("text") text: String? = null,
            @Query("extras") extras: String = "url_s, url_o"
    ): Single<PhotosResponseEntity>

    companion object {
        fun create(): FlickrApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.flickr.com/")
                    .build()

            return retrofit.create(FlickrApiService::class.java)
        }
    }
}