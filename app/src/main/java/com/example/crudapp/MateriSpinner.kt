package com.example.crudapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crudapp.databinding.ActivityMateriSpinnerBinding


class MateriSpinner : AppCompatActivity() {
    lateinit var binding: ActivityMateriSpinnerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMateriSpinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Spinner"

        val spinerBinding =binding.spiner
        val nama = binding.tvnama
        val submit = binding.submit

        spinerBinding.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val nama1 = spinerBinding.selectedItem.toString()
                submit.setOnClickListener {
                    nama.text = nama1
                    Toast.makeText(applicationContext, "anda memilih $nama1", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        binding.hapus.setOnClickListener {
            binding.tvnama.text = ""
        }
    }
}