package com.dimaszulfa.batiknusantara.motive

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dimaszulfa.batiknusantara.R
import com.dimaszulfa.batiknusantara.data.MotiveEntity
import com.dimaszulfa.batiknusantara.databinding.FragmentUserMotiveBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class UserMotiveFragment : Fragment() {

    private lateinit var database: DatabaseReference
    private lateinit var motiveArrayList: ArrayList<MotiveEntity>
    private lateinit var binding: FragmentUserMotiveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        database = Firebase.database.reference
        binding = FragmentUserMotiveBinding.inflate(inflater)
        binding.rvMotive.layoutManager = binding.rvMotive.getCarouselLayoutManager()
        binding.rvMotive.setHasFixedSize(true)
        binding.rvMotive.apply {
            set3DItem(true)
            setAlpha(true)
            setInfinite(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        motiveArrayList = arrayListOf<MotiveEntity>()
        getMotiveData()

    }

    private fun getMotiveData() {
        database = FirebaseDatabase.getInstance().getReference("motive")
        database.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(motiveSnapshot in snapshot.children){
                        val motive = motiveSnapshot.getValue(MotiveEntity::class.java)
                        motiveArrayList.add(motive!!)
                    }
                    binding.rvMotive.adapter = UserMotiveAdapter(requireContext(),motiveArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }

}