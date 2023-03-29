package kz.shopanov.yelnar.flickr.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
import kz.shopanov.yelnar.flickr.presentation.ui.imagelist.ImagesFragment

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: ImagesFragment)
}