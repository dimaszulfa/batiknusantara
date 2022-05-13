package com.dimaszulfa.batiknusantara

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dimaszulfa.batiknusantara.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {


    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cv1.setOnClickListener {  }
        binding.cv2.setOnClickListener {  }
        binding.cv3.setOnClickListener {  }
        binding.cv4.setOnClickListener {  }
    }


}