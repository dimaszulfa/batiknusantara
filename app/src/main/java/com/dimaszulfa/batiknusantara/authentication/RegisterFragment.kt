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
import com.dimaszulfa.batiknusantara.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth
    private val mainNavController: NavController? by lazy{ activity?.findNavController(R.id.nav_host)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater)

        auth = FirebaseAuth.getInstance()

        val database = Firebase.database
        val myRef = database.getReference("message").child("KEPOLU")

        myRef.setValue(arrayListOf("KEPO", "HELLO"))

// Read from the database
        /*       myRef.addValueEventListener(object: ValueEventListener {

                   override fun onDataChange(snapshot: DataSnapshot) {
                       // This method is called once with the initial value and again
                       // whenever data at this location is updated.
                       val value = snapshot.getValue<String>()
                       Log.d(TAG, "Value is: " + value)
                   }

                   override fun onCancelled(error: DatabaseError) {
                       Log.w(TAG, "Failed to read value.", error.toException())
                   }

               })*/

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun register() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val directions = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment3()
                mainNavController?.navigate(directions)
                Toast.makeText(requireContext(), email, Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }
}