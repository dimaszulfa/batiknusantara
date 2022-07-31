package com.dimaszulfa.batiknusantara.user.quiz

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.dimaszulfa.batiknusantara.R
import com.dimaszulfa.batiknusantara.data.MotiveEntity
import com.dimaszulfa.batiknusantara.data.QuizEntity
import com.dimaszulfa.batiknusantara.databinding.FragmentQuizQuestionsBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.lang.StringBuilder


private var _binding: FragmentQuizQuestionsBinding ?= null
val binding get() = _binding!!
private lateinit var db : DatabaseReference
private lateinit var dataQuiz: ArrayList<QuizEntity>
private var mCurrentPosition: Int = 1
private var mSelectedOptionPosition: Int = 0

//motiveArrayList = arrayListOf<MotiveEntity>()

class QuizQuestionsFragment : Fragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuizQuestionsBinding.inflate(layoutInflater)
        dataQuiz = arrayListOf<QuizEntity>()
        db = Firebase.database.reference
        callDatabase()
//        setQuestion()
        return binding.root
    }

    private fun setQuestion() {
        txtViewSettings(true)

        val question = dataQuiz.get(mCurrentPosition - 1)


        if (mCurrentPosition == dataQuiz!!.size) {
            binding.btnSubmit.text = "Finish"
        } else {
            binding.btnSubmit.text = "Submit"
        }

        binding.progressBar.max = dataQuiz.size
        binding.progressBar.progress = mCurrentPosition
        binding.tvProgress.text =  StringBuilder("$mCurrentPosition" + "/" + dataQuiz.size)

        binding.tvQuestion.text = question.quizQuestion
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour

        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)
    }


    private fun callDatabase() {
        db = FirebaseDatabase.getInstance().getReference("quiz")
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        val motive = data.getValue(QuizEntity::class.java)
                        dataQuiz.add(motive!!)
                    }
                    setQuestion()
                    Log.d("TAG", dataQuiz.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        dataQuiz.clear()
        mCurrentPosition = 1
        mSelectedOptionPosition = 0


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(binding.tvOptionOne, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(binding.tvOptionTwo, 2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(binding.tvOptionThree, 3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(binding.tvOptionFour, 4)
            }
            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= dataQuiz!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            Toast.makeText(
                                requireContext(),
                                "You have successfully completed the Quiz", Toast.LENGTH_SHORT
                            ).show()
//                            val intent = Intent(this,MainActivity::class.java)
//                            startActivity(intent)
                        }
                    }
                } else {
                    val question = dataQuiz?.get(mCurrentPosition - 1)
                    if (1 != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition)
                    }
//                    answerView(question.correctAnswer)
                    txtViewSettings(false)
                    if (mCurrentPosition == dataQuiz!!.size) {
                        binding.btnSubmit.text = "Finish"
                    } else {
                        binding.btnSubmit.text = "Go to next question"
                    }
                    mSelectedOptionPosition = 0
                }

            }
        }
    }

    private fun answerView(mSelectedOptionPosition: Int) {

    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
    }


    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptionOne)
        options.add(1, binding.tvOptionTwo)
        options.add(2, binding.tvOptionThree)
        options.add(3, binding.tvOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
        }


    }

    private fun txtViewSettings(params: Boolean){
        binding.tvQuestion.isEnabled = params
        binding.tvOptionOne.isEnabled = params
        binding.tvOptionTwo.isEnabled = params
        binding.tvOptionThree.isEnabled = params
        binding.tvOptionFour.isEnabled = params
    }
}