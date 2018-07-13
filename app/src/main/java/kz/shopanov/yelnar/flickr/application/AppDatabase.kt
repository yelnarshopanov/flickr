package kz.shopanov.yelnar.flickr.application

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import kz.shopanov.yelnar.flickr.entities.SearchHistory
import kz.shopanov.yelnar.flickr.models.SearchHistoryDao


/**
 * Created by Yelnar Shopanov
 * on 11.07.2018.
 */

@Database(entities = arrayOf(SearchHistory::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val searchHistoryDao: SearchHistoryDao
}