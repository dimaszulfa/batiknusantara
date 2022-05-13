package com.dimaszulfa.batiknusantara

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dimaszulfa.batiknusantara.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


/*        binding.btnRegister.setOnClickListener {
            toRegister()
        }*/
    }

    private fun toRegister() {
        val intent = Intent(this@MainActivity,RegisterActivity::class.java)
        startActivity(intent)    }
}