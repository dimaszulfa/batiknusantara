package com.dimaszulfa.batiknusantara

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.dimaszulfa.batiknusantara.databinding.FragmentHomeBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!
    private val mainNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.cv1.setOnClickListener {
            goToHistory()

        }

        binding.cv2.setOnClickListener {
            goToMotive()
        }
        binding.cv3.setOnClickListener {
            goToVariety()
        }

        binding.cv4.setOnClickListener {
            goToQuiz()
        }

        binding.cv6.setOnClickListener {
            goToTreatment()
        }

        binding.cv5.setOnClickListener {
            goToPuzzle()
        }

        binding.cv7.setOnClickListener {
            goToAbout()

        }


    }

    private fun goToMotive() {
        val directions = HomeFragmentDirections.actionHomeFragmentToUserMotiveFragment()
        mainNavController?.navigate(directions)
    }

    private fun goToHistory() {
        val directions = HomeFragmentDirections.actionHomeFragmentToUserHistoryFragment()
        mainNavController?.navigate(directions)
    }

    private fun goToTreatment() {
        val direction = HomeFragmentDirections.actionHomeFragmentToTreatmentFragment()
        mainNavController?.navigate(direction)      }

    private fun goToQuiz() {
        val direction = HomeFragmentDirections.actionHomeFragmentToQuizFragment2()
        mainNavController?.navigate(direction)    }

    private fun goToPuzzle() {
        val direction = HomeFragmentDirections.actionHomeFragmentToNPuzzleActivity()
        mainNavController?.navigate(direction)
    }


    private fun goToVariety() {
        val directions = HomeFragmentDirections.actionHomeFragmentToUserVarietyFragment()
        mainNavController?.navigate(directions)
    }

    private fun goToAbout() {
        val directions = HomeFragmentDirections.actionHomeFragmentToAboutFragment()
        mainNavController?.navigate(directions)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}