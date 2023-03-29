package kz.shopanov.yelnar.flickr.data.api

import kz.shopanov.yelnar.flickr.data.model.ImageDTO
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrService {

    @GET("services/rest/?method=flickr.photos.search&nojsoncallback=1&format=json")
    fun search(
        @Query("api_key") apiKey: String,
        @Query("text") text: String,
        @Query("extras") extras: String = "url_s, url_o"
    ): ImageDTO

    companion object {
        fun create(): FlickrService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.flickr.com/")
                .build()

            return retrofit.create(FlickrService::class.java)
        }
    }
}