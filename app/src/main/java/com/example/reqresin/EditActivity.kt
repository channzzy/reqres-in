package com.example.reqresin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.reqresin.api.ApiConfig
import com.example.reqresin.model.DetailUser
import com.example.reqresin.request.RequestCreateUser
import com.example.reqresin.request.UserRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditActivity : AppCompatActivity() {

    var edName : EditText? = null;
    var edJob : EditText? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val btnEdit = findViewById<Button>(R.id.btn_edit_data)
        val intent = intent
        val idUser = intent.getIntExtra("idUser", 0)

        if(idUser != 0){
            println(idUser)
            ApiConfig.getService().detailUser(idUser).enqueue(object :
                Callback<DetailUser> {
                override fun onResponse(call: Call<DetailUser>, response: Response<DetailUser>) {
                    if(response.code() == 200){
                        findViewById<EditText>(R.id.edit_name).setText(response.body()?.data?.firstName + " " + response.body()?.data?.lastName)
                        findViewById<EditText>(R.id.edit_job).setText(response.body()?.data?.email)
                    }
                }

                override fun onFailure(call: Call<DetailUser>, t: Throwable) {
                    println(t.localizedMessage)
                }

            })
        edName = findViewById(R.id.edit_name)
        edJob = findViewById(R.id.edit_job)
        btnEdit.setOnClickListener {
            editUser(idUser)
        }
        }
    }
    fun editUser(id : Int){
        if(edName?.text.toString() == "" || edJob?.text.toString() == ""){
            Toast.makeText(this@EditActivity, "Harap isi semua data", Toast.LENGTH_SHORT).show()
            return
        }
        val userRequest = UserRequest(edName?.text.toString(),edName?.text.toString())
        ApiConfig.getService().updateUser(userRequest,id).enqueue(object : Callback<RequestCreateUser>{
            override fun onResponse(call: Call<RequestCreateUser>, response: Response<RequestCreateUser>) {
                if(response.code() == 200){
                    Toast.makeText(this@EditActivity, "Data berhasil diedit ${response.body()?.name} ${response.body()?.job} ${response.body()?.updatedAt}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@EditActivity,MainActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<RequestCreateUser>, t: Throwable) {
                println(t.localizedMessage)
            }

        })

    }
}