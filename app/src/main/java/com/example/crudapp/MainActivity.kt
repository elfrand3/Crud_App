package com.example.crudapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudapp.databinding.ActivityMainBinding
import com.example.crudapp.model.ResponseSave
import com.example.crudapp.networking.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.icSave.setOnClickListener{
            if(binding.icUser.text.isEmpty()||binding.icPass.text.isEmpty()){
                Toast.makeText(this, "Lengkapi data terlebih dahulu....", Toast.LENGTH_SHORT).show()
            }else{
                saveData(
                    binding.icUser.text.toString(),
                    binding.icPass.text.toString(),
                )
            }
        }

    }

    fun saveData(name: String, job: String){
        ApiConfig.getInstance().saveData(name, job).enqueue(object : Callback<ResponseSave>{
            override fun onResponse(call: Call<ResponseSave>, response: Response<ResponseSave>) {
                val id = response.body()?.id
                val nm = response.body()?.name
                val jb = response.body()?.job
                val dt = response.body()?.createdAt
                val intent = Intent(this@MainActivity, Tampilan::class.java)
                intent.putExtra("id", id)
                intent.putExtra("name", nm)
                intent.putExtra("job", jb)
                intent.putExtra("date", dt)
                startActivity(intent)
            }

            override fun onFailure(call: Call<ResponseSave>, t: Throwable) {

            }

        })
    }
}