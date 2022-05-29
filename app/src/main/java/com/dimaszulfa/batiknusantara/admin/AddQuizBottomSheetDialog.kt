package com.dimaszulfa.batiknusantara.admin

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.dimaszulfa.batiknusantara.R
import com.dimaszulfa.batiknusantara.databinding.FragmentAddQuizBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddQuizBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddQuizBottomSheetDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddQuizBottomSheetDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddData.setOnClickListener {
            binding.etQuestion.isEnabled = false
            binding.etOptionOne.isEnabled = false
            binding.etOptionTwo.isEnabled = false
            binding.etOptionThree.isEnabled = false
            binding.etOptionFour.isEnabled = false
            selectTheCorrectAnswer()
        }


    }

    private fun selectTheCorrectAnswer() {
        AlertDialog.Builder(requireContext())
            .setTitle("Judul")
            .setMessage("Pesan yang tampil")
            .setPositiveButton("Ya", DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(requireContext(), "Jalankan perintah ketika user memilih tombol Ya", Toast.LENGTH_LONG).show()
                binding.spCorrect.visibility = View.VISIBLE
                with(binding){
                    val op1 = binding.etOptionOne.text.toString()
                    val op2 = binding.etOptionTwo.text.toString()
                    val op3 = binding.etOptionThree.text.toString()
                    val op4 = binding.etOptionFour.text.toString()
                    val dataOptions = arrayListOf<String>(op1,op2,op3,op4)
                    val aa = ArrayAdapter(requireContext(), androidx.transition.R.layout.support_simple_spinner_dropdown_item, dataOptions)
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spCorrect!!.adapter = aa
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