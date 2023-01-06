package com.dimaszulfa.batiknusantara.ui.variety

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimaszulfa.batiknusantara.data.VarietyEntity
import com.dimaszulfa.batiknusantara.databinding.FragmentUserVarietyBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


private var _binding: FragmentUserVarietyBinding? = null
val binding get() = _binding!!
private lateinit var database: DatabaseReference
private lateinit var varietyArrayList: ArrayList<VarietyEntity>
class UserVarietyFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserVarietyBinding.inflate(layoutInflater)
        database = Firebase.database.reference

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        varietyArrayList = arrayListOf<VarietyEntity>()
        getVarietyData()

    }


    private fun getVarietyData() {
        database = FirebaseDatabase.getInstance().getReference("variety")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (treatmentSnapshot in snapshot.children) {
                        val variety = treatmentSnapshot.getValue(VarietyEntity::class.java)
                        varietyArrayList.add(variety!!)
                        binding.rvVariety.adapter = VarietyAdapter(varietyArrayList)
                        binding.rvVariety.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
                        binding.rvVariety.setHasFixedSize(true)
                    }

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