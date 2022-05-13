package com.dimaszulfa.batiknusantara

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.viewpager2.widget.ViewPager2
import com.dimaszulfa.batiknusantara.databinding.FragmentIntroductionBinding


class IntroductionFragment : Fragment() {


    private lateinit var binding: FragmentIntroductionBinding
    private var titleList = mutableListOf<String>()
    private var contentList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIntroductionBinding.inflate(layoutInflater, container, false)
        createIntroductionData()
        PreferenceManager.getDefaultSharedPreferences(requireContext()).apply {
            if(getBoolean("COMPLETEDLANDING",false)){
                toHomeFragment()
            }
        }
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.viewPager.adapter = SectionPagerAdapter(titleList, contentList)
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val indicator = binding.indicator
        indicator.setViewPager2(binding.viewPager)

        binding.cvButton.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(requireContext()).edit().apply {
                putBoolean("COMPLETEDLANDING", true)
                apply()
            }
            toHomeFragment()
        }
    }

    private fun toHomeFragment() {
        findNavController().navigate(R.id.action_introductionFragment_to_homeFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    fun addToList(title: String, content: String) {
        titleList.add(title)
        contentList.add(content)
    }

    fun createIntroductionData() {
        addToList(
            "Sejarah Batik",
            "Apakah anda tahu bagaimana asal mula sejarah batik? Apabila tidak, mari kita cari tahu!"
        )
        addToList(
            "Motif Batik",
            "Ketahui motif-motif Batik Nusantara yang dikembangkan oleh Balai Besar Kerajinan dan Batik !"
        )
        addToList(
            "Jenis Batik",
            "Mari berkenalan lebih dalam terkait proses pembuatan Batik Nusantara!"
        )
        addToList(
            "Quiz",
            "Ketahui motif-motif Batik Nusantara yang dikembangkan oleh Balai Besar Kerajinan dan Batik !"
        )
    }

}