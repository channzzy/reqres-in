package com.example.reqresin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.reqresin.api.ApiConfig
import com.example.reqresin.model.LoginResponse
import com.example.reqresin.request.LoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    var txtEmail : EditText? = null;
    var txtPassword : EditText? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        txtEmail = findViewById(R.id.email)
        txtPassword = findViewById(R.id.password)

        val sessionManager = SessionManager(this)
        btnLogin.setOnClickListener {
            login(sessionManager)
        }

    }
    fun login(sessionManager : SessionManager){
        println(txtEmail?.text.toString())
        println(txtPassword?.text.toString())

        if(txtEmail?.text.toString() == "" || txtPassword?.text.toString() == ""){
            Toast.makeText(this@LoginActivity, "Harap isi semua data", Toast.LENGTH_SHORT).show()
            return
        }
        val loginRequest = LoginRequest(txtEmail?.text.toString(),txtPassword?.text.toString())
        ApiConfig.getService().login(loginRequest).enqueue(object :
            Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                println(                response.code()
                )
                if(response.code() == 400){
                    Toast.makeText(this@LoginActivity, "Anda berhasil login", Toast.LENGTH_SHORT).show()
                    var token = "QpwL5tke4Pnpja7X4"
                    if(token != null){
                        sessionManager.sessionLogin(token)
                    }
                    val intent = Intent(this@LoginActivity,MainActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }
}