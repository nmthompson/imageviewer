package com.bigdudeapps.pictureviewer.ui.model

import android.os.Parcel
import android.os.Parcelable
import com.bigdudeapps.pictureviewer.ui.IImageDataItem

class PixaImage(val previewUrl: String?,
                val likes: Int,
                val id: Int,
                val views: Int,
                val tags: String?,
                val downloads: Int,
                val user: String?,
                val largeImageURL: String?) : IImageDataItem, Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(previewUrl)
        parcel.writeInt(likes)
        parcel.writeInt(id)
        parcel.writeInt(views)
        parcel.writeString(tags)
        parcel.writeInt(downloads)
        parcel.writeString(user)
        parcel.writeString(largeImageURL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PixaImage> {
        override fun createFromParcel(parcel: Parcel): PixaImage {
            return PixaImage(parcel)
        }

        override fun newArray(size: Int): Array<PixaImage?> {
            return arrayOfNulls(size)
        }
    }

}