package com.wgs.cuatcuit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.wgs.cuatcuit.R

class LoginActivity : AppCompatActivity() {

    var et_email = findViewById<EditText>(R.id.txt_email)
    var et_password = findViewById<EditText>(R.id.txtPass)
    var btn_login = findViewById<Button>(R.id.btn_login)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    private fun initView(){
        btn_login.setOnClickListener {
            onClickLogin()
        }
    }

    private fun onClickLogin(){
        val email = et_email.text
        val pass = et_password.text

        if (email.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Form cannot be empty ", Toast.LENGTH_LONG).show()
        }else{
            loginRequest()
        }
    }

    private fun loginRequest(){
        Toast.makeText(this, "Login request ", Toast.LENGTH_LONG).show()
    }

}
