package com.bigdudeapps.pictureviewer.service.model

import android.os.Parcel
import android.os.Parcelable

data class PixaPhotosData(val largeImageURL: String? = "",
                          val likes: Int = 0,
                          val id: Int = 0,
                          val views: Int = 0,
                          val pageURL: String? = "",
                          val tags: String? = "",
                          val downloads: Int = 0,
                          val user: String? = "",
                          val previewURL: String? = "") : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(largeImageURL)
        parcel.writeInt(likes)
        parcel.writeInt(id)
        parcel.writeInt(views)
        parcel.writeString(pageURL)
        parcel.writeString(tags)
        parcel.writeInt(downloads)
        parcel.writeString(user)
        parcel.writeString(previewURL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PixaPhotosData> {
        override fun createFromParcel(parcel: Parcel): PixaPhotosData {
            return PixaPhotosData(parcel)
        }

        override fun newArray(size: Int): Array<PixaPhotosData?> {
            return arrayOfNulls(size)
        }
    }
}