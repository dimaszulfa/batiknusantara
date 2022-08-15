package com.dimaszulfa.batiknusantara.user.motive

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dimaszulfa.batiknusantara.databinding.FragmentUserMotiveDetailBinding


class UserMotiveDetail : Fragment() {


    private lateinit var _binding: FragmentUserMotiveDetailBinding
    private val args by navArgs<UserMotiveDetailArgs>()
    val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserMotiveDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txTitle.text = args.title
        Log.d("TAG", args.image)
        Glide.with(requireContext()).load(args.image).into(binding.ivImage)
        binding.txDesc.text = args.description
        binding.txCategory.text = args.category
        binding.txProvince.text = args.province
    }


}