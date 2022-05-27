package com.dimaszulfa.batiknusantara.util

import android.view.View
import android.widget.TextView

object ErrorHandlerUtils {
    fun validationText(view: TextView){
        if (view.text.toString().isEmpty()){
            view.error = "Bagian ini tidak boleh kosong!"
        }
    }
}