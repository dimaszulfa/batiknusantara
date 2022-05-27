package com.dimaszulfa.batiknusantara.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.dimaszulfa.batiknusantara.R
import com.dimaszulfa.batiknusantara.databinding.FragmentLoginBinding
import com.dimaszulfa.batiknusantara.util.ErrorHandlerUtils
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentLoginBinding
    private val mainNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener {
            ErrorHandlerUtils.validationText(binding.etEmail)
            ErrorHandlerUtils.validationText(binding.etPassword)
            login()
        }
    }

    private fun login() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            Toast.makeText(requireContext(), "Anda Berhasil Login", Toast.LENGTH_SHORT).show()
            val directions = LoginFragmentDirections.actionLoginFragment3ToDashboardAdminFragment()
            mainNavController?.navigate(directions)
        }.addOnFailureListener { error ->
            Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

}