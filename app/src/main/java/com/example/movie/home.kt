package com.example.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.databinding.ActivityHomeBinding

class home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val receivedUsername = intent.getStringExtra("USERNAME")

        if (receivedUsername != null) {
            binding.usrview.text = "Hello, $receivedUsername" // Menampilkan username menggunakan ViewBinding
        }
    }
}