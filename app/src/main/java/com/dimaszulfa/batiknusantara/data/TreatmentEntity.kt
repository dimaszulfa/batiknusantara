package com.dimaszulfa.batiknusantara.data

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@IgnoreExtraProperties
@Parcelize
data class TreatmentEntity(
    val step: String? = null,
    val image: String? = null,
    val step_desc: String? = null


): Parcelable