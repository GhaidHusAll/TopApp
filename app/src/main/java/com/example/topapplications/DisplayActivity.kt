package com.example.topapplications

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.topapplications.databinding.ActivityDisplayBinding
import com.squareup.picasso.Picasso

class DisplayActivity : AppCompatActivity() {
    private lateinit var binding :ActivityDisplayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.tvName.text = intent.getStringExtra("appName")
        binding.tvSummary.text = intent.getStringExtra("appSummary")
        Picasso.get().load(intent.getStringExtra("appLogo")).into(binding.ivLogo)
        binding.btnBack.setOnClickListener {
            val toMain = Intent(this,MainActivity::class.java)
            startActivity(toMain)
        }
    }

}