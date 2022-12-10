package com.example.crudapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.crudapp.model.DataKabupaten
import com.example.crudapp.model.DataProvinsi
import com.example.crudapp.model.ResponseKabupaten
import com.example.crudapp.model.ResponseProvinsi
import com.example.crudapp.networking.ApiSpinner
import kotlinx.android.synthetic.main.activity_spinner_api.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpinnerApi : AppCompatActivity() {

    var listProvinsi: ArrayList<DataProvinsi> = arrayListOf()
    var namaProvinsi: ArrayList<String> = arrayListOf()

    var listKb: ArrayList<DataKabupaten> = arrayListOf()
    var namaKb: ArrayList<String> = arrayListOf()
//
//    var listKec: ArrayList<DataKecamatan> = arrayListOf()
//    var namaKec: ArrayList<String> = arrayListOf()
//
//    var listDes: ArrayList<DataDesa> = arrayListOf()
//    var namaDes: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner_api)

        spinProv.onItemSelectedListener= object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(this@SpinnerApi, listProvinsi[p2].nama, Toast.LENGTH_SHORT).show()
                Toast.makeText(this@SpinnerApi, listKb[p2].nama, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        ApiSpinner.endpoint.getProvinsi().enqueue(
            object : Callback<ResponseProvinsi>{
            override fun onResponse(
                call: Call<ResponseProvinsi>,
                response: Response<ResponseProvinsi>
            ) {
                if(response.isSuccessful){
                    val response : ResponseProvinsi? = response.body()
                    onResultProvinsi(response!!.provinsi)
                }
            }

            override fun onFailure(call: Call<ResponseProvinsi>, t: Throwable) {

            }
        })

        ApiSpinner.endpoint.getKb(id_provinsi = String()).enqueue(
            object : Callback<ResponseKabupaten>{
            override fun onResponse(
                call: Call<ResponseKabupaten>,
                response: Response<ResponseKabupaten>
            ) {
                if(response.isSuccessful){
                    val response : ResponseKabupaten? = response.body()
                    onResultKabupaten(response!!.kota_kabupaten)
                }
            }

            override fun onFailure(call: Call<ResponseKabupaten>, t: Throwable) {

            }
        })
    }


    private fun onResultProvinsi(provinsi: List<DataProvinsi>) {
        namaProvinsi.add("Pilih Provinsi")
        listProvinsi.add(DataProvinsi(0, "Pilih Provinsi"))
        if(provinsi != null){
            for (i in provinsi){
                namaProvinsi.add(i.nama)
                listProvinsi.add(DataProvinsi(i.id, i.nama))
            }
        }
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, namaProvinsi)
        spinProv.adapter = adapter
    }

    private fun onResultKabupaten(kotaKabupaten: List<DataKabupaten>) {
        namaKb.add("Pilih Kota/Kabupaten")
        listKb.add(DataKabupaten(0,0, "Pilih Kota/Kabupaten"))
        if(kotaKabupaten != null){
            for (a in kotaKabupaten){
                namaKb.add(a.nama)
                listKb.add(DataKabupaten(a.id, a.id_provinsi, a.nama))
            }
        }
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, namaKb)
        spinKota.adapter = adapter
    }
}