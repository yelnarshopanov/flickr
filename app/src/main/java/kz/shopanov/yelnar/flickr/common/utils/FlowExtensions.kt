package kz.shopanov.yelnar.flickr.common.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch

fun <T> Flow<T>.launchWhenStarted(lifecycleOwner: LifecycleOwner, action: suspend (value: T) -> Unit) =
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            this@launchWhenStarted.collectLatest(action)
        }
    }

inline fun <T, R> Flow<List<T>>.mapTo(crossinline transform: suspend (value: T) -> R): Flow<List<R>> =
    map { list -> list.map { transform(it) } }

inline fun <T, R> Flow<List<T>>.mapToLatest(crossinline transform: suspend (value: T) -> R): Flow<List<R>> =
    mapLatest { list -> list.map { transform(it) } }