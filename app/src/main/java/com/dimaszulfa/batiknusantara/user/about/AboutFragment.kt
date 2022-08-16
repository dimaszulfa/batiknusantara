package com.dimaszulfa.batiknusantara.user.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dimaszulfa.batiknusantara.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}