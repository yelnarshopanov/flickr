package kz.shopanov.yelnar.flickr.presentation.ui.imagelist

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kz.shopanov.yelnar.flickr.R
import kz.shopanov.yelnar.flickr.common.base.BaseViewModel
import kz.shopanov.yelnar.flickr.domain.usecase.GetImagesUseCase
import kz.shopanov.yelnar.flickr.domain.model.Image
import kz.shopanov.yelnar.flickr.presentation.ui.imagelist.adapter.ImageItem
import timber.log.Timber

internal class ImagesViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase
) : BaseViewModel() {

    private val _actions = Channel<ImagesAction>(Channel.BUFFERED)
    val actions: Flow<ImagesAction> = _actions.receiveAsFlow()

    private val _images = MutableStateFlow<List<Image>>(emptyList())
    val images: StateFlow<List<ImageItem>> = _images.map { images ->
        images.map { image ->
            ImageItem(
                image = image,
                onItemClicked = {}
            )
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun onViewCreated() {
        if (_images.value.isEmpty()) {
            refresh()
        }
    }

    private fun refresh() {
        viewModelScope.launchSafe(
            body = { _images.value = getImagesUseCase(Unit) },
            handleError = { error ->
                Timber.e(error, "Could not refresh data")
                _actions.trySend(ImagesAction.ShowError(R.string.app_name))
            }
        )
    }
}

internal sealed class ImagesAction {
    data class ShowError(@StringRes val errorResId: Int) : ImagesAction()
}