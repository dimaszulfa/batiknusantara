package com.dimaszulfa.batiknusantara.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dimaszulfa.batiknusantara.R
import com.dimaszulfa.batiknusantara.data.HistoryEntity
import com.dimaszulfa.batiknusantara.databinding.FragmentHistoryBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var db: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        db = Firebase.database.reference
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnUpload.setOnClickListener {
            addHistoryToDatabase()
        }
    }

    private fun addHistoryToDatabase() {
        val title = binding.txTitle.text.toString()
        val desc = binding.txDesc.text.toString()
        val data = HistoryEntity(title, desc)
        db.child("history").child(title).setValue(data).addOnSuccessListener {
            Toast.makeText(requireContext(), "Data Berhasil Dimasukkan!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
        }

    }

}