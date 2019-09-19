package com.docotel.muhadif.simpleculinary.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Culinary(
    val name : String,
    val image : Int,
    val city : String,
    val content : String
) : Parcelable