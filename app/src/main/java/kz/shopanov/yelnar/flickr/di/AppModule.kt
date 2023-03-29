package kz.shopanov.yelnar.flickr.di

import dagger.Binds
import dagger.Module
import javax.inject.Singleton
import kz.shopanov.yelnar.flickr.data.repository.ImageRepository
import kz.shopanov.yelnar.flickr.data.repository.ImageRepositoryImpl

@Module
interface AppModule {

    @Singleton
    @Binds
    fun bindD(imageRepositoryImpl: ImageRepositoryImpl): ImageRepository

//    companion object {
//        private const val DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd"
//        private const val DATE_FORMAT_DD_MM_YYYY = "MM/dd/yyyy HH:mm:ss"
//
//        @Singleton
//        @Provides
//        fun provideGson(): Gson =
//            GsonBuilder()
//                .registerTypeAdapter(
//                    StringDate::class.java,
//                    StringDateTypeAdapter(DATE_FORMAT_YYYY_MM_DD) { StringDate(it) }
//                )
//                .registerTypeAdapter(
//                    StringDateEx::class.java,
//                    StringDateTypeAdapter(DATE_FORMAT_DD_MM_YYYY) { StringDateEx(it) }
//                )
//                .registerTypeAdapter(Date::class.java, UnixEpochDateTypeAdapter())
//                .registerTypeAdapter(UnixTime::class.java, UnixTimeTypeAdapter())
//                .create()
//    }
}