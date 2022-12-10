package com.example.crudapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class MateriCRS : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materi_crs)

        val r_minuman = findViewById<RadioGroup>(R.id.r_minuman)
//
//        val ms = findViewById<RadioButton>(R.id.m_sg)
//        val mj = findViewById<RadioButton>(R.id.m_js)
//        val ma = findViewById<RadioButton>(R.id.m_am)

        val mb = findViewById<CheckBox>(R.id.bakso)
        val mst = findViewById<CheckBox>(R.id.Soto)
        val msb = findViewById<CheckBox>(R.id.Seblak)

        val btn_pesan = findViewById<Button>(R.id.pesan)
        val tx_menupesanan = findViewById<TextView>(R.id.menu_pesanan)
        val btn_hapus = findViewById<Button>(R.id.hapus)

        btn_hapus.setOnClickListener{
            r_minuman.clearCheck()
            tx_menupesanan.text = "Kosong"
            mb.isChecked = false
            mst.isChecked = false
            msb.isChecked = false

        }
        btn_pesan.setOnClickListener{
            val cekMinumanRadioButtonId = r_minuman.checkedRadioButtonId
            val menu = findViewById<RadioButton>(cekMinumanRadioButtonId)

            val m = mb.isChecked
            val ms = mst.isChecked
            val mm = msb.isChecked

            val hasilPesanan = "Anda Memilih : \n" +
//                    "${menu.text}" +
                    (if (menu != null)menu.text else "Tidak ada pesanan")+
                    (if (m) "\n bakso " else "")+
                    (if (ms) "\n Soto " else "")+
                    (if (mm) "\n Seblak " else "")
            tx_menupesanan.text = hasilPesanan
        }


    }
}