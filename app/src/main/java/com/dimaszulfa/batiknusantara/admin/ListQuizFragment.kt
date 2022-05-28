package com.dimaszulfa.batiknusantara.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimaszulfa.batiknusantara.R
import com.dimaszulfa.batiknusantara.databinding.FragmentListQuizBinding


class ListQuizFragment : Fragment() {

    private lateinit var binding: FragmentListQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListQuizBinding.inflate(layoutInflater)
        binding.btnAddQuiz.setOnClickListener {
            val bottomSheet = AddQuizBottomSheetDialog()
            bottomSheet.showNow(childFragmentManager, "Fragment")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}