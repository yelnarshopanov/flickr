package kz.shopanov.yelnar.flickr.viewModels

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kz.shopanov.yelnar.flickr.R
import kz.shopanov.yelnar.flickr.entities.PhotoEntity

/**
 * Created by Yelnar Shopanov
 * on 12.07.2018.
 */

class PhotoAdapter(private val context: Context) : BaseAdapter() {
    var cellSize: Int = 0

    private val dataSet: ArrayList<PhotoEntity> = ArrayList()

    override fun getCount(): Int = dataSet.count()

    override fun getItem(position: Int): Any? = dataSet.get(position)

    override fun getItemId(position: Int): Long = 0L

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView: ImageView

        if (convertView == null) {
            imageView = LayoutInflater.from(context).inflate(R.layout.photo_list_item,
                    parent, false) as ImageView
            imageView.layoutParams = ViewGroup.LayoutParams(cellSize, cellSize)
        } else {
            imageView = convertView as ImageView
        }

        Picasso.get().cancelRequest(imageView)
        Picasso.get().load(dataSet[position].url_s).into(imageView)

        return imageView
    }

    fun refresh(photos: List<PhotoEntity>?) {
        dataSet.clear()

        if (photos != null) {
            dataSet.addAll(photos)
        }

        notifyDataSetChanged()
    }
}