package com.example.crudapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.example.crudapp.databinding.ActivityTampilanBinding
import com.example.crudapp.model.DataItem
import com.example.crudapp.model.ResponseGet
import com.example.crudapp.networking.ApiService
import kotlinx.android.synthetic.main.activity_tampilan.*
import kotlinx.android.synthetic.main.list_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Tampilan : AppCompatActivity() {
    private val binding: ActivityTampilanBinding by lazy {
        ActivityTampilanBinding.inflate(layoutInflater)
    }

    val mainAdapter = FileAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        mainAdapter.setOnItemClickListener(object : FileAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val builder = AlertDialog.Builder(this@Tampilan)

                //Kebutuhan konten alert dialog
                builder.setTitle(R.string.jd)
                builder.setMessage(R.string.pd)
                builder.setIcon(R.drawable.ic_warning)

                //Membuat action Button pada Alert Dialog
                //Teks (_ , _) ini merupakan nama parameter
                builder.setPositiveButton("Ya") { _, _ ->
                    Toast.makeText(applicationContext, "Klik ya", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("Tidak") { _, _ ->
                    Toast.makeText(applicationContext, "Klik Tidak", Toast.LENGTH_SHORT).show()
                }
                builder.setNeutralButton("Batal") { _, _ ->
                    Toast.makeText(applicationContext, "Klik Batal", Toast.LENGTH_SHORT).show()
                }

                //Menampilkan kelas alert dialog
                val tampil : AlertDialog = builder.create()
                tampil.setCancelable(false)
                tampil.show()
            }
        })

        binding.tvdata.setOnClickListener {
            val builder = AlertDialog.Builder(this)

            //Kebutuhan konten alert dialog
            builder.setTitle(R.string.jd)
            builder.setMessage(R.string.pd)
            builder.setIcon(R.drawable.ic_warning)

            //Membuat action Button pada Alert Dialog
            //Teks (_ , _) ini merupakan nama parameter
            builder.setPositiveButton("Ya") { _, _ ->
                Toast.makeText(applicationContext, "Klik ya", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Tidak") { _, _ ->
                Toast.makeText(applicationContext, "Klik Tidak", Toast.LENGTH_SHORT).show()
            }
            builder.setNeutralButton("Batal") { _, _ ->
                Toast.makeText(applicationContext, "Klik Batal", Toast.LENGTH_SHORT).show()
            }

            //Menampilkan kelas alert dialog
            val tampil : AlertDialog = builder.create()
            tampil.setCancelable(false)
            tampil.show()
        }

        supportActionBar?.hide()
        rv_list.layoutManager = GridLayoutManager(this, 1)
        rv_list.adapter = mainAdapter
        getData()

        val intent = intent
        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")
        val job = intent.getStringExtra("job")
        val date = intent.getStringExtra("date")

        binding.tvdata.text = "id: $id \nname: $name \njob: $job \ndate: $date"


    }

    private fun getData() {
        ApiService.endpoint.getData().enqueue(object : Callback<ResponseGet> {
            override fun onResponse(call: Call<ResponseGet>, response: Response<ResponseGet>) {
                if(response.isSuccessful){
                    val responseGet:ResponseGet? = response.body()
                    onResultData(responseGet!!)
                }
            }

            override fun onFailure(call: Call<ResponseGet>, t: Throwable) {
            }

        })
    }
    private fun onResultData(responseGet: ResponseGet) {
        val vertical = responseGet.data
        mainAdapter.getData(vertical as List<DataItem>)
    }

}