package com.wgs.cuatcuit.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.wgs.cuatcuit.R
import com.wgs.cuatcuit.model.core.Resource
import com.wgs.cuatcuit.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

//    val resource = MutableLiveData<Resource<Any>>()
    private val viewModel by lazy { ViewModelProviders.of(this).get(LoginViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initViewModel() {
        viewModel.resource.observe(this, Observer {
            when (it?.status) {
                Resource.LOADING -> onLoading()
                Resource.SUCCESS -> onSuccess()
                Resource.ERROR -> onFailure(it.error)
            }
        })
    }

    private fun fetchData() {
        viewModel.postLogin(email = txt_email.text.toString(), pass = txtPass.text.toString())
    }

    private fun onLoading() {
    }

    private fun onSuccess() {
    }

    private fun onFailure(t: Throwable) {
    }

    private fun initView(){
        btn_login.setOnClickListener {
            onClickLogin()
        }

        txt_sign_up.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun onClickLogin(){
        if (txt_email.text.isEmpty() || txtPass.text.isEmpty()){
            Toast.makeText(this, "Form cannot be empty ", Toast.LENGTH_LONG).show()
        }else{
            loginRequest()
        }
    }

    private fun loginRequest(){
        fetchData()
        Toast.makeText(this, "Login request ", Toast.LENGTH_LONG).show()
    }

}
