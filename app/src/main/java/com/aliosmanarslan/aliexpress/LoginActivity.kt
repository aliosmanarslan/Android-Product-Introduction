package com.aliosmanarslan.aliexpress

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.aliosmanarslan.aliexpress.databinding.ActivityLoginBinding
import com.aliosmanarslan.aliexpress.models.UserLogin
import com.aliosmanarslan.aliexpress.service.Service
import com.aliosmanarslan.aliexpress.utils.ApiClient
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var bind:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val service = ApiClient.getClient().create(Service::class.java)

        bind.loginBtn.setOnClickListener {

            val email = bind.usernameLoginTextbox.text.toString().trim()
            val pass = bind.passwordLoginTextbox.text.toString().trim()

            if(email.isEmpty()){
                bind.usernameLoginTextbox.error = "Error: Enter Your Email!"
                bind.usernameLoginTextbox.requestFocus()
                return@setOnClickListener
            }

            if(pass.isEmpty()){
                bind.passwordLoginTextbox.error = "Error: Enter Your Password!"
                bind.passwordLoginTextbox.requestFocus()
                return@setOnClickListener
            }

            val dataService = service.userLogin(email, pass)
            dataService.enqueue(object : Callback<UserLogin>{
                override fun onResponse(call: Call<UserLogin>, response: Response<UserLogin>) {
                    if(response.isSuccessful){
                        val u = response.body()
                        val durum = u?.user?.get(0)?.durum

                        if(durum == true){
                            UserData.customerId =  u?.user?.get(0)?.bilgiler?.userId
                            Toast.makeText(this@LoginActivity, "Login successful", Toast.LENGTH_SHORT).show()
                            val i = Intent(this@LoginActivity, ProductActivity::class.java)
                            startActivity(i)
                            finish()
                        }else{
                            Snackbar.make(bind.root, "The information you entered is incorrect, please try again.", Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<UserLogin>, t: Throwable) {
                    Log.e("onFailure", "onFailure: $t", )
                }

            })
        }








        bind.loginToRegisterText.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }

    }
}