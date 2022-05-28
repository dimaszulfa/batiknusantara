package com.dimaszulfa.batiknusantara.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimaszulfa.batiknusantara.databinding.FragmentAddQuizBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddQuizBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddQuizBottomSheetDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddQuizBottomSheetDialogBinding.inflate(layoutInflater)
        return binding.root
    }

}