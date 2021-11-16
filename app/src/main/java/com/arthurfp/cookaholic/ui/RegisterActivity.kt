package com.arthurfp.cookaholic.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.arthurfp.cookaholic.databinding.ActivityLoginBinding
import com.arthurfp.cookaholic.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    lateinit var strUsername: String
    lateinit var strPassword: String

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        btnReadLogin.setOnClickListener {
//
//            strUsername = txtUsername.text.toString().trim()
//
//            loginViewModel.getLoginDetails(context, strUsername)!!.observe(this, Observer {
//
//                if (it == null) {
//                    lblReadResponse.text = "Data Not Found"
//                    lblUseraname.text = "- - -"
//                    lblPassword.text = "- - -"
//                }
//                else {
//                    lblUseraname.text = it.Username
//                    lblPassword.text = it.Password
//
//                    lblReadResponse.text = "Data Found Successfully"
//                }
//            })
//        }

    }
}