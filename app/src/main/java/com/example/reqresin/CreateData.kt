package com.example.reqresin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.reqresin.api.ApiConfig
import com.example.reqresin.request.RequestCreateUser
import com.example.reqresin.request.UserRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

    class CreateData : AppCompatActivity() {
        var txtName : EditText? = null;
        var txtJob : EditText? = null;
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_data)

        val btnSave = findViewById<Button>(R.id.btn_save)
        txtName = findViewById(R.id.create_name)
            txtJob = findViewById(R.id.crate_job)
            btnSave.setOnClickListener {
            addUser()
        }

    }
    fun addUser(){
        if(txtName?.text.toString() == "" || txtJob?.text.toString() == ""){
            Toast.makeText(this@CreateData, "Harap isi semua data", Toast.LENGTH_SHORT).show()
            return
        }
            val userRequest = UserRequest(txtName?.text.toString(),txtJob?.text.toString())
            ApiConfig.getService().createData(userRequest).enqueue(object : Callback<RequestCreateUser>{
                override fun onResponse(call: Call<RequestCreateUser>, response: Response<RequestCreateUser>) {
                    if(response.code() == 201){
                        Toast.makeText(this@CreateData, "Data berhasil ditambahkan ${response.body()?.name} ${response.body()?.job} ${response.body()?.createdAt}", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@CreateData,MainActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<RequestCreateUser>, t: Throwable) {
                    println(t.localizedMessage)
                }

            })

    }
}