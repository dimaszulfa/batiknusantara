package com.dimaszulfa.batiknusantara.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.dimaszulfa.batiknusantara.R
import com.dimaszulfa.batiknusantara.databinding.FragmentDashboardAdminBinding


class DashboardAdminFragment : Fragment() {

    private lateinit var binding: FragmentDashboardAdminBinding
    private val mainNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDashboardAdminBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnMotif.setOnClickListener {
            toMotiveFragment()
        }

        binding.btnHistory.setOnClickListener {
            toHistoryFragment()
        }

        binding.btnQuiz.setOnClickListener {
            toQuizFragment()
        }
    }

    private fun toHistoryFragment() {
        val directions =
            DashboardAdminFragmentDirections.actionDashboardAdminFragmentToHistoryFragment2()
        mainNavController?.navigate(directions)
    }

    private fun toMotiveFragment() {
        val directions =
            DashboardAdminFragmentDirections.actionDashboardAdminFragmentToMotiveFragment()
        mainNavController?.navigate(directions)
    }

    private fun toQuizFragment(){
        val directions = DashboardAdminFragmentDirections.actionDashboardAdminFragmentToListQuizFragment()
        mainNavController?.navigate(directions)

    }

}