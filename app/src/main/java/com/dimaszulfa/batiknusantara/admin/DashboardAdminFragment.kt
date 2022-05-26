package com.dimaszulfa.batiknusantara.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.dimaszulfa.batiknusantara.R
import com.dimaszulfa.batiknusantara.databinding.FragmentDashboardAdminBinding


class DashboardAdminFragment : Fragment() {

    private lateinit var binding: FragmentDashboardAdminBinding
    private val mainNavController: NavController? by lazy {  activity?.findNavController(R.id.nav_host)}

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
            val directions = DashboardAdminFragmentDirections.actionDashboardAdminFragmentToMotiveFragment()
            mainNavController?.navigate(directions)
        }
    }

}