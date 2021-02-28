package com.example.calculatesamples.data

import android.os.Parcel
import android.os.Parcelable

data class SampleData(val startInterval: Int, val stepInterval: Int, val sampling: List<Int>) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        TODO("sampling")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(startInterval)
        parcel.writeInt(stepInterval)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SampleData> {
        override fun createFromParcel(parcel: Parcel): SampleData {
            return SampleData(parcel)
        }

        override fun newArray(size: Int): Array<SampleData?> {
            return arrayOfNulls(size)
        }
    }
}