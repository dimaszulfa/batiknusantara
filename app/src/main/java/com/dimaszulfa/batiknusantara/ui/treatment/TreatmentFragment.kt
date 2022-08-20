package com.dimaszulfa.batiknusantara.ui.treatment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.dimaszulfa.batiknusantara.data.TreatmentEntity
import com.dimaszulfa.batiknusantara.databinding.FragmentTreatmentBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class TreatmentFragment : Fragment() {

    private lateinit var database: DatabaseReference
    private var _binding: FragmentTreatmentBinding? = null
    private lateinit var treatmentArrayList: ArrayList<TreatmentEntity>

    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        database = Firebase.database.reference

        _binding = FragmentTreatmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        treatmentArrayList = arrayListOf<TreatmentEntity>()
        getTreatmentData()

    }


    private fun getTreatmentData() {
        database = FirebaseDatabase.getInstance().getReference("treatment")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (treatmentSnapshot in snapshot.children) {
                        val treatment = treatmentSnapshot.getValue(TreatmentEntity::class.java)
                        treatmentArrayList.add(treatment!!)
                    }
                    binding.viewPager.adapter = SectionPagerAdapter(requireContext(), treatmentArrayList)
                    binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    val indicator = binding.indicator
                    indicator.setViewPager2(binding.viewPager)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}