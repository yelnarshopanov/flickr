package kz.shopanov.yelnar.flickr.viewModels

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.databinding.BaseObservable
import android.os.Build
import android.provider.SearchRecentSuggestions
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.view.ViewTreeObserver
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kz.shopanov.yelnar.flickr.R
import kz.shopanov.yelnar.flickr.application.Constants
import kz.shopanov.yelnar.flickr.application.SimpleRecentSuggestionsProvider
import kz.shopanov.yelnar.flickr.entities.PhotoEntity
import kz.shopanov.yelnar.flickr.flickrApi.SearchRepository
import kz.shopanov.yelnar.flickr.flickrApi.SearchRepositoryProvider
import kz.shopanov.yelnar.flickr.views.MainActivity
import kz.shopanov.yelnar.flickr.views.PhotoViewActivity


/**
 * Created by Yelnar Shopanov
 * on 11.07.2018.
 */

class SearchDataBinding(private var activity: MainActivity) : BaseObservable() {

    private var suggestions = SearchRecentSuggestions(activity, SimpleRecentSuggestionsProvider.AUTHORITY,
            SimpleRecentSuggestionsProvider.MODE)
    private val repository: SearchRepository by lazy {
        SearchRepositoryProvider.provideSearchRepository()
    }
    val photoAdapter: PhotoAdapter = PhotoAdapter(activity)
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        setupGridView()
    }

    fun setupSearchView(searchView: SearchView) {
        val searchManager = activity.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.componentName))
    }

    private fun setupGridView() {
        val gridView: GridView = activity.findViewById(R.id.gridView)
        gridView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                var cellSize = activity.resources.getDimensionPixelSize(R.dimen.cell_size)
                val spacing = activity.resources.getDimensionPixelSize(R.dimen.cell_spacing)
                val width = gridView.width - gridView.paddingRight - gridView.paddingLeft

                val numColumns = Math.floor((width / (cellSize + spacing)).toDouble()).toInt()

                if (numColumns > 0) {
                    cellSize = width / numColumns - spacing
                    gridView.numColumns = numColumns
                    photoAdapter.cellSize = cellSize
                }

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    gridView.viewTreeObserver.removeGlobalOnLayoutListener(this)
                } else {
                    gridView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            }
        })
    }

    fun activityOnNewIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)

            if (!TextUtils.isEmpty(query)) {
                handleSearch(query)
            }
        }
    }

    fun activityOnDestroy() {
        compositeDisposable.clear()
    }

    private fun handleSearch(query: String) {
        suggestions.saveRecentQuery(query, null)

        compositeDisposable.add(
                repository.searchPhotos(Constants.API_KEY, query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ result ->
                            photoAdapter.refresh(result.photos?.photos)
                        }, { error ->
                            Toast.makeText(activity, error.message, Toast.LENGTH_SHORT).show()
                        })
        )
    }

    val gridViewOnItemClickListener: AdapterView.OnItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                val photo = parent.getItemAtPosition(position) as PhotoEntity
                val intent = Intent(activity, PhotoViewActivity::class.java)
                intent.putExtra(Constants.EXTRA_URL, photo.url_o)
                activity.startActivity(intent)
            }
}