package com.dimaszulfa.batiknusantara.user.about

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dimaszulfa.batiknusantara.data.AboutEntity
import com.dimaszulfa.batiknusantara.data.HistoryEntity
import com.dimaszulfa.batiknusantara.databinding.FragmentAboutBinding
import com.dimaszulfa.batiknusantara.databinding.FragmentUserHistoryBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    val binding get() = _binding!!
    private lateinit var db: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(layoutInflater)
        db = Firebase.database.reference
        binding.pgProgressBar.visibility = View.VISIBLE
        binding.cvAbout.visibility = View.INVISIBLE
        getDataAbout()
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    fun getDataAbout(){
        db = FirebaseDatabase.getInstance().getReference("about")
        db.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    binding.pgProgressBar.visibility = View.INVISIBLE
                    binding.cvAbout.visibility = View.VISIBLE
                    for(about in snapshot.children){
                        val data = about.getValue<AboutEntity>()
                        binding.textDesc.text = data!!.about
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