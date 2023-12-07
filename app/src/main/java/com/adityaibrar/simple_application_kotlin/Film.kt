package com.adityaibrar.simple_application_kotlin

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val name: String?,
    val description: String?,
    val photo: Int?,
    val duration: String?,
    val rating: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    companion object : Parceler<Film> {
        override fun Film.write(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeString(description)
            photo?.let { parcel.writeInt(it) }
            parcel.writeString(duration)
            parcel.writeString(rating)
        }

        override fun create(parcel: Parcel): Film {
            return Film(parcel)
        }
    }
}
