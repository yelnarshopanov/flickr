package kz.shopanov.yelnar.flickr.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Yelnar Shopanov
 * on 11.07.2018.
 */

@Entity
class SearchHistory {
    @PrimaryKey
    var query: String? = null
    var datetime: Long? = null
}