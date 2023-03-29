package kz.shopanov.yelnar.flickr.data.model

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("photo")
    val photo: String
)