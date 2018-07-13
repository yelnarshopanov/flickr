package kz.shopanov.yelnar.flickr.models

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import kz.shopanov.yelnar.flickr.entities.SearchHistory

/**
 * Created by Yelnar Shopanov
 * on 11.07.2018.
 */

@Dao
interface SearchHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg searchHistories: SearchHistory)

    @Query("SELECT * FROM searchHistory WHERE `query` LIKE :search")
    fun getAllSearchHistory(search: String): List<SearchHistory>
}