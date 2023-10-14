package com.example.movie

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.databinding.ActivityDetailBinding

class detail : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Menambahkan OnClickListener ke tombol "Beli"
        binding.beli.setOnClickListener {
            // Buat Intent untuk pindah ke aktivitas pembayaran (PaymentActivity)
            val intent = Intent(this, payment::class.java)
            startActivity(intent)
        }
    }
}