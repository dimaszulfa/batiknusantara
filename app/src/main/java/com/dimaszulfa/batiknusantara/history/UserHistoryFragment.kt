package com.dimaszulfa.batiknusantara.history

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dimaszulfa.batiknusantara.R
import com.dimaszulfa.batiknusantara.data.HistoryEntity
import com.dimaszulfa.batiknusantara.databinding.FragmentUserHistoryBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class UserHistoryFragment : Fragment() {

    private lateinit var binding: FragmentUserHistoryBinding
    private lateinit var db: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserHistoryBinding.inflate(layoutInflater)
        db = Firebase.database.reference
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseDatabase.getInstance().getReference("history")
        db.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(history in snapshot.children){
                        val data = history.getValue<HistoryEntity>()
                        binding.txTitle.text = data!!.title
                        binding.txDesc.text = data!!.desc
                        Log.i("TAG", snapshot.toString())
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }


}