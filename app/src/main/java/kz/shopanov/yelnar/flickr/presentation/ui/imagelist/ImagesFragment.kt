package kz.shopanov.yelnar.flickr.presentation.ui.imagelist

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import javax.inject.Inject
import kz.shopanov.yelnar.flickr.R
import kz.shopanov.yelnar.flickr.appComponent
import kz.shopanov.yelnar.flickr.common.utils.launchWhenStarted
import kz.shopanov.yelnar.flickr.common.utils.toast
import kz.shopanov.yelnar.flickr.common.viewbinding.viewBinding
import kz.shopanov.yelnar.flickr.databinding.FragmentImagesBinding
import kz.shopanov.yelnar.flickr.presentation.ui.imagelist.adapter.ImageAdapter

class ImagesFragment : Fragment(R.layout.fragment_images) {

    private val viewBinding by viewBinding(FragmentImagesBinding::bind)

    @Inject
    internal lateinit var viewModel: ImagesViewModel

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)

        val imageAdapter = ImageAdapter()

        rvUsers.setHasFixedSize(true)
        rvUsers.adapter = imageAdapter
//        rvUsers.addItemDecoration()

        viewModel.actions.launchWhenStarted(viewLifecycleOwner) { action ->
            when (action) {
                is ImagesAction.ShowError -> toast(action.errorResId)
            }
        }

        viewModel.images.launchWhenStarted(viewLifecycleOwner) { imageAdapter.submitList(it) }

        viewModel.onViewCreated()
    }
}