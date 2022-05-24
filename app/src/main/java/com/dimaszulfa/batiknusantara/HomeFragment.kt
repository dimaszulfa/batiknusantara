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
import androidx.navigation.findNavController
import com.dimaszulfa.batiknusantara.databinding.FragmentHomeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {

    val db = Firebase.firestore

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
        binding.cv2.setOnClickListener {

            val docRef = db.collection("users").document("1rKC4FcDyfeJHGpj3kLE")
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
//                        Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data?.get("geopoint")}")
                        val data = document.data?.get("geopoint") as List<*>

                        data.forEach {
                            Log.d(TAG, "DocumentSnapshot data: ${it}")

                        }
                    } else {
                        Log.d(ContentValues.TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(ContentValues.TAG, "get failed with ", exception)
                }

        }
        binding.cv3.setOnClickListener {
            goToLocations()
        }
        binding.cv4.setOnClickListener {
            goToQuiz()
        }



    }

    private fun goToLocations() {
        val direction = HomeFragmentDirections.actionHomeFragmentToLocationFragment()
        mainNavController?.navigate(direction)
    }

    private fun goToQuiz(){
        val directions = HomeFragmentDirections.actionHomeFragmentToQuizFragment()

        mainNavController?.navigate(directions)
    }


}