package com.example.movie

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    // Waktu tunda (dalam milidetik) untuk splash screen
    private val SPLASH_TIMEOUT: Long = 3000 // Misalnya, 3 detik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Menggunakan Handler untuk menunda tampilan splash screen
        Handler(Looper.getMainLooper()).postDelayed({
            // Setelah waktu tunda, kita akan memulai LoginActivity
            val loginIntent = Intent(this, login::class.java)
            startActivity(loginIntent)

            // Tutup SplashActivity agar tidak kembali ke sini saat tombol "kembali" ditekan
            finish()
        }, SPLASH_TIMEOUT)
    }
}