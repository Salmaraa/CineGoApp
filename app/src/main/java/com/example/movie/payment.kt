package com.example.movie

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.databinding.ActivityPaymentBinding
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class payment : AppCompatActivity() {
    private val calendar = Calendar.getInstance()
    private lateinit var binding: ActivityPaymentBinding

    private val kursiOptions = arrayOf(
        "Regular 2D - 30000",
        "3D - 50000",
        "IMAX - 60000",
        "Premiere - 100000",
        "Dolby Atmos - 120000"
    )

    private val paymentMethods = arrayOf(
        "BANK MANDIRI",
        "BANK BCA",
        "BANK BNI",
        "BANK BRI",
        "DANA",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val bioskopArray = resources.getStringArray(R.array.nama_bioskop)
        val bioskopAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, bioskopArray)
        bioskopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.bioskop.adapter = bioskopAdapter

        val seatAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, kursiOptions)
        seatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.seat.adapter = seatAdapter

        val paymentMethodAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, paymentMethods)
        paymentMethodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.paymentMethod.adapter = paymentMethodAdapter

        binding.seat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateTextView(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle jika tidak ada yang dipilih (opsional)
            }
        }

        binding.datepick.setOnClickListener {
            showDatePicker()
        }

        binding.orderSummary.setOnClickListener {
            val intent = Intent(this, orderSummary::class.java)
            val position = binding.seat.selectedItemPosition
            val kursiOption = kursiOptions[position]
            val selectedSeat = binding.seat.selectedItem.toString() // Mendapatkan pilihan kursi dari Spinner
            val paymentMethod = binding.paymentMethod.selectedItem.toString() // Mendapatkan pilihan metode pembayaran dari Spinner
            val actualPay = kursiOption.substringAfter("- ").trim().toInt()
            val typeOfSeat = kursiOption.substringBefore(" -")

            intent.putExtra("actualPay", actualPay)
            intent.putExtra("selectedSeat", selectedSeat)
            intent.putExtra("paymentMethod", paymentMethod)
            intent.putExtra("typeOfSeat", typeOfSeat)

            startActivity(intent)
        }
    }

    private fun showDatePicker() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
                binding.datepick.setText(dateFormat.format(calendar.time))
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun updateTextView(position: Int) {
        val kursiOption = kursiOptions[position]
        val actualPay = kursiOption.substringAfter("- ").trim().toInt()
        val formattedPrice = NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(actualPay)

        binding.pilseat.text = "Pilihan Kursi: $kursiOption"
        binding.harusbayar.text = "Nominal yang harus dibayar: $formattedPrice"

        if (position == 0) {
            binding.pilseat.visibility = View.VISIBLE
            binding.harusbayar.visibility = View.GONE
        } else {
            binding.pilseat.visibility = View.GONE
            binding.harusbayar.visibility = View.VISIBLE
        }
    }

    private fun navigateToOrderSummary() {
        val intent = Intent(this, orderSummary::class.java)

        val position = binding.seat.selectedItemPosition
        val kursiOption = kursiOptions[position]
        val actualPay = kursiOption.substringAfter("- ").trim().toInt()
        intent.putExtra("actualPay", actualPay)

        startActivity(intent)
    }
}
