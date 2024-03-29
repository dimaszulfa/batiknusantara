package com.dimaszulfa.batiknusantara.ui.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.dimaszulfa.batiknusantara.databinding.FragmentResultQuizBinding

class ResultQuizFragment : Fragment() {


    private var _binding: FragmentResultQuizBinding?= null
    val binding get() = _binding!!
    private val args by navArgs<ResultQuizFragmentArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResultQuizBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDataFromQuizQuestions()
    }

    private fun initDataFromQuizQuestions() {
        binding.tvAccuracy.text = StringBuilder(args.accuracy + "%")
        binding.tvScore.text = StringBuilder(args.score + "/20")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}