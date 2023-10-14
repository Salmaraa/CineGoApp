package com.example.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.databinding.ActivityLoginBinding

class login : AppCompatActivity() {
    private val validUsername = "nama"
    private val validPassword = "niu"

    private lateinit var viewBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val usernameEditText = viewBinding.unameedit
        val passwordEditText = viewBinding.pwedit
        val loginButton = viewBinding.loginButton

        with(viewBinding){

        }
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.contains(" ") || !password.matches(Regex("\\d+"))) {
                // Validasi input pengguna
                Toast.makeText(this, "Invalid username and password", Toast.LENGTH_SHORT).show()
            } else if (username == validUsername && password == validPassword) {
                // Login berhasil, arahkan ke halaman berikutnya
                Log.d("MyApp", "Login berhasil. Arahkan ke halaman home.")
                val intent = Intent(this@login, home::class.java)
                intent.putExtra("USERNAME", username) // Menambahkan username ke Intent
                startActivity(intent)
                finish()
            }
        }
    }
}