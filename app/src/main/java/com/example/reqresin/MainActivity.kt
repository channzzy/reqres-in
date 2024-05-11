package com.example.reqresin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reqresin.adapter.UserAdapter
import com.example.reqresin.api.ApiConfig
import com.example.reqresin.api.ApiService
import com.example.reqresin.model.DataItem
import com.example.reqresin.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Objects

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyleView = findViewById<RecyclerView>(R.id.rv)
        val btnAddUser = findViewById<Button>(R.id.add_user)
        var sessionManager = SessionManager(this)
        println(sessionManager.getToken())


        val intent = intent

        ApiConfig.getService().getUsers().enqueue(object  : Callback<UserModel>{
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if(response.isSuccessful){
                    val response = response.body()
                    val dataUser = response?.data
                    val userAdapter = UserAdapter(dataUser)
                    recyleView.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        adapter = userAdapter
                    }
                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                println(t.localizedMessage)
            }

        })
        btnAddUser.setOnClickListener {
            val intent = Intent(this,CreateData::class.java)
            startActivity(intent)
        }
    }
}