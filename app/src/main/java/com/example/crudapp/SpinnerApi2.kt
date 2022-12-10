package com.example.crudapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.example.crudapp.databinding.ActivityMainBinding
import com.example.crudapp.databinding.ActivitySpinnerApi2Binding
import com.example.crudapp.model.ResponseKabupaten
import com.example.crudapp.model.ResponseKecamatan
import com.example.crudapp.model.ResponseKelurahan
import com.example.crudapp.model.ResponseProvinsi
import com.example.crudapp.networking.ApiSpinner
import com.example.crudapp.networking.ApiUser
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpinnerApi2 : AppCompatActivity() {

    private val binding : ActivitySpinnerApi2Binding by lazy{
        ActivitySpinnerApi2Binding.inflate(layoutInflater)
    }

    private val listProv = ArrayList<String>()

    private val idProv = ArrayList<String>()

    private val listkab = ArrayList<String>()

    private val idkab = ArrayList<String>()

    private val listkec = ArrayList<String>()

    private val idkec = ArrayList<String>()

    private val listkel = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fetchProv()
//
//        listkec.add("---Pilih Kecamatan---")
//        val kecSpin = ArrayAdapter(
//            this@SpinnerApi2, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
//            listkec
//        )
//        kecSpin.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
//        binding.spinKec.adapter = kecSpin

        binding.spinProv.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                listkab.clear()
                fetchKab(idProv[p2])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        binding.spinKota.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                listkec.clear()
                if (p2 != 0){
                    fetchkec(idkab[p2])
                }else{
                    fetchkec("")
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        binding.spinKec.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                listkel.clear()
                if (p2 != 0){
                    fetchkel(idkec[p2])
                }else{
                    fetchkel("")
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }
    private fun fetchProv(){
        listProv.add("---Pilih Provinsi---")
        ApiSpinner.endpoint.getProvinsi().enqueue(object : Callback<ResponseProvinsi>{
            override fun onResponse(
                call: Call<ResponseProvinsi>,
                response: Response<ResponseProvinsi>
            ) {
                if (response.isSuccessful){
                    response.body()?.provinsi?.forEach{ provinsi ->
                        idProv.add((provinsi.id - 1).toString())
                        listProv.add(provinsi.nama)
                    }
                    val provinsiSpin = ArrayAdapter(
                        this@SpinnerApi2, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                        listProv
                    )
                    provinsiSpin.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
                    binding.spinProv.adapter = provinsiSpin
                }
            }

            override fun onFailure(call: Call<ResponseProvinsi>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    internal fun fetchKab(idProvinsi : String){
        listkab.add("---Pilih Kabupaten---")
        ApiSpinner.endpoint.getKb(idProvinsi).enqueue(object : Callback<ResponseKabupaten>{
            override fun onResponse(
                call: Call<ResponseKabupaten>,
                response: Response<ResponseKabupaten>
            ) {
                if (response.isSuccessful){
                    response.body()?.kota_kabupaten?.forEach{ kab ->
                        idkab.add((kab.id - 1).toString())
                        listkab.add(kab.nama)
                        Log.d("kabupaten",kab.nama)
                    }
                    val kabSpin = ArrayAdapter(
                        this@SpinnerApi2, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                        listkab
                    )
                    kabSpin.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
                    binding.spinKota.adapter = kabSpin
                }
            }

            override fun onFailure(call: Call<ResponseKabupaten>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    internal fun fetchkec(id_kota : String){
        listkec.add("---Pilih Kecamatan---")
        ApiSpinner.endpoint.getKec(id_kota).enqueue(object : Callback<ResponseKecamatan>{
            override fun onResponse(
                call: Call<ResponseKecamatan>,
                response: Response<ResponseKecamatan>
            ) {
                if (response.isSuccessful){
                    response.body()?.kecamatan?.forEach{ kec ->
                        idkec.add((kec.id - 1).toString())
                        listkec.add(kec.nama)
                        Log.d("kecamatan",kec.nama)
                    }
                    val kecSpin = ArrayAdapter(
                        this@SpinnerApi2, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                        listkec
                    )
                    kecSpin.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
                    binding.spinKec.adapter = kecSpin
                }
            }

            override fun onFailure(call: Call<ResponseKecamatan>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    internal fun fetchkel(id_kecamatan : String){
        listkel.add("---Pilih Desa---")
        ApiSpinner.endpoint.getKel(id_kecamatan).enqueue(object : Callback<ResponseKelurahan>{
            override fun onResponse(
                call: Call<ResponseKelurahan>,
                response: Response<ResponseKelurahan>
            ) {
                if (response.isSuccessful){
                    response.body()?.kelurahan?.forEach{ kel ->
                        listkel.add(kel.nama)
                        Log.d("kelurahan",kel.nama)
                    }
                    val kelSpin = ArrayAdapter(
                        this@SpinnerApi2, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                        listkel
                    )
                    kelSpin.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
                    binding.spinDes.adapter = kelSpin
                }
            }

            override fun onFailure(call: Call<ResponseKelurahan>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}