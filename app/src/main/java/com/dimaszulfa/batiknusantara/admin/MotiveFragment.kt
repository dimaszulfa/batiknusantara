package com.dimaszulfa.batiknusantara.admin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dimaszulfa.batiknusantara.R
import com.dimaszulfa.batiknusantara.data.MotiveEntity
import com.dimaszulfa.batiknusantara.databinding.FragmentMotiveBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage


class MotiveFragment : Fragment() {

    val REQUEST_CODE = 100
    private lateinit var binding: FragmentMotiveBinding
    private lateinit var database: DatabaseReference
    private lateinit var storage: StorageReference
    private lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMotiveBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = Firebase.database.reference
        storage = Firebase.storage.reference
        binding.btnUpload.setOnClickListener {
            addMotiveToDatabase()
        }
        binding.btnChooseImage.setOnClickListener {
            //read data once
/*            database.child("motive").child("Batik Mega mendung").get().addOnSuccessListener {
                Log.i("firebase", "Got value ${it.child("title").getValue()}")
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
            }*/
            addImage()
        }

    }

    private fun addImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            binding.imagePreview.setImageURI(data?.data)
            data?.data.let{
                if (it != null) {
                    imageUri = it
                }
            }
        }
    }

    private fun addMotiveToDatabase(){
        val title = binding.txTitle.text.toString()
        val desc = binding.txDesc.text.toString()

        val key = database.push().key
        storage.child(key+ ".jpg").putFile(imageUri).addOnSuccessListener {
            storage.child(key + ".jpg").downloadUrl.addOnSuccessListener {
                val image = it.toString()
                val motiveData = MotiveEntity(title, desc, image)
                database.child("motive").child(title).setValue(motiveData).addOnSuccessListener {
                    Toast.makeText(requireContext(),"Berhasil Menambahkan Motif!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}