package com.dimaszulfa.batiknusantara

import android.content.ContentValues
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

    val db = Firebase.firestore
    val auth = Firebase.auth
    private lateinit var binding : FragmentHomeBinding
    private val mainNavController: NavController? by lazy{ activity?.findNavController(R.id.nav_host)}

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



        binding.cv1.setOnClickListener { // Cr
            val db = Firebase.firestore
// eate a new user with a first and last name
            val user = hashMapOf(
                "first" to "Ada",
                "last" to "Lovelace",
                "born" to 1815
            )

// Add a new document with a generated ID
            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }

        binding.cv1.setOnClickListener {
            val directions = HomeFragmentDirections.actionHomeFragmentToHistoryFragment()

            mainNavController?.navigate(directions)
        }

        binding.cv2.setOnClickListener {
            val directions = HomeFragmentDirections.actionHomeFragmentToUserMotiveFragment()
            mainNavController?.navigate(directions)
        }
        binding.cv3.setOnClickListener {
            goToLocations()
        }
        binding.cv4.setOnClickListener {
//            goToQuiz()
            var directions: NavDirections
            if (auth.currentUser == null ){
                directions = HomeFragmentDirections.actionHomeFragmentToLoginFragment33()
            } else {
                directions = HomeFragmentDirections.actionHomeFragmentToMotiveFragment()
            }
            mainNavController?.navigate(directions)


        }

        binding.cv6.setOnClickListener {
            goToAbout()
        }

        binding.cv5.setOnClickListener {
            goToRegister()
        }



    }

    private fun goToRegister() {
        val direction = HomeFragmentDirections.actionHomeFragmentToRegisterFragment()
        mainNavController?.navigate(direction)
    }

    private fun goToLocations() {
        val direction = HomeFragmentDirections.actionHomeFragmentToLocationFragment()
        mainNavController?.navigate(direction)
    }

    private fun goToQuiz(){
        val directions = HomeFragmentDirections.actionHomeFragmentToQuizFragment()

        mainNavController?.navigate(directions)
    }

    private fun goToAbout(){
        val directions = HomeFragmentDirections.actionHomeFragmentToAboutFragment()
        mainNavController?.navigate(directions)
    }


}