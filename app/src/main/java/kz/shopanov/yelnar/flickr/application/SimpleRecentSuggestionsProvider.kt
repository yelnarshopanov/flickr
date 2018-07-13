package kz.shopanov.yelnar.flickr.application

import android.content.SearchRecentSuggestionsProvider


/**
 * Created by Yelnar Shopanov
 * on 11.07.2018.
 */

class SimpleRecentSuggestionsProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = "kz.shopanov.yelnar.flickr.SimpleRecentSuggestionsProvider"
        const val MODE = DATABASE_MODE_QUERIES
    }
}