package com.dimaszulfa.batiknusantara.data

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@IgnoreExtraProperties
@Parcelize
data class MotiveEntity(
    val title: String? = null,
    val description: String? = null,
    val image: String? = null,
    val province: String? = null,
    val category: String? = null
): Parcelable
