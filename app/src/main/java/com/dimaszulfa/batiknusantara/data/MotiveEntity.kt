package com.dimaszulfa.batiknusantara.data

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class MotiveEntity(
    val title: String? = null,
    val description: String? = null,
    val image: String? = null
)
