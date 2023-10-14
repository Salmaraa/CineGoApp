package com.example.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.databinding.ActivityOrderSummaryBinding
import java.text.NumberFormat
import java.util.Locale

class orderSummary : AppCompatActivity() {
    private lateinit var binding: ActivityOrderSummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderSummaryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Mendapatkan data yang dikirimkan dari aktivitas sebelumnya (payment)
        val actualPay = intent.getIntExtra("actualPay", 0)
        val selectedSeat = intent.getStringExtra("selectedSeat")
        val paymentMethod = intent.getStringExtra("paymentMethod")
        val typeOfSeat = intent.getStringExtra("typeOfSeat")
        val convenienceFee = calculateConvenienceFee()

        // Tambahkan convenience fee ke actual pay
        val totalPay = actualPay + convenienceFee

        // Menampilkan data di TextView pada halaman OrderSummary
        binding.seatt.text = "Seat: $selectedSeat"
        binding.paymet.text = "Payment Method: $paymentMethod"
        binding.tyseatt.text = "Type of Seat: $typeOfSeat"
        binding.fee.text = "Convenience Fee: ${formatPrice(convenienceFee)}"
        binding.actually.text = "Actual Pay: ${formatPrice(totalPay)}"
    }

    private fun calculateConvenienceFee(): Int {
        // Tambahkan logika perhitungan convenience fee sesuai dengan kebutuhan Anda
        // Di sini, kita hanya contohkan penggunaan nilai konstan 5000
        return 5000
    }

    private fun formatPrice(price: Int): String {
        // Fungsi untuk memformat harga ke dalam format mata uang
        return NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(price)
    }
}
