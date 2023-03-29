package kz.shopanov.yelnar.flickr.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import kz.shopanov.yelnar.flickr.data.api.FlickrRestAPI
import kz.shopanov.yelnar.flickr.data.api.FlickrService
import kz.shopanov.yelnar.flickr.data.dataSource.ImageRemoteDataSource
import kz.shopanov.yelnar.flickr.data.repository.ImageRepository
import kz.shopanov.yelnar.flickr.data.repository.ImageRepositoryImpl
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
internal interface AppModule {

    @Singleton
    @Binds
    fun bindRepository(imageRepositoryImpl: ImageRepositoryImpl): ImageRepository

    @Singleton
    @Binds
    fun bindImageRemoteDataSource(flickrRestAPI: FlickrRestAPI): ImageRemoteDataSource

    companion object {

        @Singleton
        @Provides
        fun provideFlickrService(retrofit: Retrofit): FlickrService =
            retrofit.create(FlickrService::class.java)
    }
}