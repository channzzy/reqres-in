package com.example.reqresin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.reqresin.api.ApiConfig
import com.example.reqresin.api.ApiService
import com.example.reqresin.model.DetailUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)
        val intent = intent
        val idUser = intent.getIntExtra("id", 0)
        var image = findViewById<ImageView>(R.id.img_detail)
        var btnDelete = findViewById<Button>(R.id.btn_delete)
        var btnEdit = findViewById<Button>(R.id.btn_edit)


        if(intent.getIntExtra("id", 0) != 0){
            ApiConfig.getService().detailUser(idUser).enqueue(object : Callback<com.example.reqresin.model.DetailUser>{
                override fun onResponse(call: Call<DetailUser>, response: Response<DetailUser>) {
                    println(response.code())
                    if(response.code() == 200){
                        findViewById<TextView>(R.id.txt_detail_name).text = response.body()?.data?.firstName + " " + response.body()?.data?.lastName
                        findViewById<TextView>(R.id.txt_detail_email).text = response.body()?.data?.email
                        Glide.with(image)
                            .load(response.body()?.data?.avatar)
                            .error(R.drawable.ic_launcher_background)
                            .into(image)
                    }
                }

                override fun onFailure(call: Call<DetailUser>, t: Throwable) {
                    println(t.localizedMessage)
                }

            })
            btnDelete.setOnClickListener {
                ApiConfig.getService().deleteUser(idUser).enqueue(object : Callback<com.example.reqresin.model.DetailUser>{
                    override fun onResponse(call: Call<DetailUser>, response: Response<DetailUser>) {
                        println(response.code())
                        if(response.code() == 204){
                            Toast.makeText(this@DetailUser, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@DetailUser,MainActivity::class.java)
                            startActivity(intent)
                        }
                    }

                    override fun onFailure(call: Call<DetailUser>, t: Throwable) {
                        println(t.localizedMessage)
                    }
                })
            }
            btnEdit.setOnClickListener {
                val intent = Intent(this@DetailUser,EditActivity::class.java)
                intent.putExtra("idUser",idUser)
                startActivity(intent)
            }
        }
    }
}