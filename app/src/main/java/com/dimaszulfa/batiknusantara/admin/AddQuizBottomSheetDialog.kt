package com.dimaszulfa.batiknusantara.admin

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.dimaszulfa.batiknusantara.HomeFragmentDirections
import com.dimaszulfa.batiknusantara.R
import com.dimaszulfa.batiknusantara.data.QuizEntity
import com.dimaszulfa.batiknusantara.databinding.FragmentAddQuizBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddQuizBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddQuizBottomSheetDialogBinding
    private val mainNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host) }
    private lateinit var db: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddQuizBottomSheetDialogBinding.inflate(layoutInflater)
        db = Firebase.database.reference
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddData.setOnClickListener {
            if(binding.btnAddData.text.toString() == "Selesai"){
                val question = binding.etQuestion.text.toString()
                val op1 = binding.etOptionOne.text.toString()
                val op2 = binding.etOptionTwo.text.toString()
                val op3 = binding.etOptionThree.text.toString()
                val op4 = binding.etOptionFour.text.toString()
                val data = QuizEntity(question,op1,op2,op3,op4,binding.spCorrect.selectedItem.toString())
                val key = db.push().key
                db.child("quiz").child(key!!).setValue(data).addOnSuccessListener {
                    Toast.makeText(requireContext(),binding.spCorrect.selectedItemPosition.toString(), Toast.LENGTH_SHORT).show()
                    this.dismiss()
                }
            }else{
                binding.etQuestion.isEnabled = false
                binding.etOptionOne.isEnabled = false
                binding.etOptionTwo.isEnabled = false
                binding.etOptionThree.isEnabled = false
                binding.etOptionFour.isEnabled = false
                selectTheCorrectAnswer()
            }
        }




    }

    private fun selectTheCorrectAnswer() {
        AlertDialog.Builder(requireContext())
            .setTitle("Apakah anda yakin?")
            .setMessage("Apabila anda telah yakin, selanjutnya pilih jawaban yang benar dari pertanyaan anda")
            .setPositiveButton("Ya", DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(requireContext(), "Jalankan perintah ketika user memilih tombol Ya", Toast.LENGTH_LONG).show()
                binding.spCorrect.visibility = View.VISIBLE



                with(binding){
                    val op1 = binding.etOptionOne.text.toString()
                    val op2 = binding.etOptionTwo.text.toString()
                    val op3 = binding.etOptionThree.text.toString()
                    val op4 = binding.etOptionFour.text.toString()
                    val dataOptions = arrayListOf(op1,op2,op3,op4)
                    val aa = ArrayAdapter(requireContext(), androidx.transition.R.layout.support_simple_spinner_dropdown_item, dataOptions)
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spCorrect!!.adapter = aa
                    btnAddData.text = "Selesai"
                }
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(requireContext(), "Jalankan perintah ketika user memilih tombol No", Toast.LENGTH_LONG).show()
                binding.etQuestion.isEnabled = true
                binding.etOptionOne.isEnabled = true
                binding.etOptionTwo.isEnabled = true
                binding.etOptionThree.isEnabled = true
                binding.etOptionFour.isEnabled = true
                binding.spCorrect.visibility = View.GONE
            })
            .show()
    }

}