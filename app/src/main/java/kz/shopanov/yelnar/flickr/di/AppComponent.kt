package kz.shopanov.yelnar.flickr.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import kz.shopanov.yelnar.flickr.presentation.ui.imagelist.ImagesFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: ImagesFragment)
}