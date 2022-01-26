package com.aliosmanarslan.aliexpress

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aliosmanarslan.aliexpress.databinding.ActivityRegisterBinding
import com.aliosmanarslan.aliexpress.models.UserRegister
import com.aliosmanarslan.aliexpress.service.Service
import com.aliosmanarslan.aliexpress.utils.ApiClient
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var bind:ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(bind.root)


        val service = ApiClient.getClient().create(Service::class.java)



        bind.registerBtn.setOnClickListener {

            val name = bind.registerNameText.text.toString().trim()
            val surname = bind.registerSurnameText.text.toString().trim()
            val phone = bind.registerPhoneText.text.toString().trim()
            val email = bind.registerMailText.text.toString().trim()
            val pass = bind.registerPassText.text.toString().trim()


            if(name.isEmpty()){
                bind.registerNameText.error = "Error: Enter Your Name!"
                bind.registerNameText.requestFocus()
                return@setOnClickListener
            }
            if(surname.isEmpty()){
                bind.registerSurnameText.error = "Error: Enter Your Surname!"
                bind.registerSurnameText.requestFocus()
                return@setOnClickListener
            }
            if(phone.isEmpty()){
                bind.registerPhoneText.error = "Error: Enter Your Phone!"
                bind.registerPhoneText.requestFocus()
                return@setOnClickListener
            }
            if(email.isEmpty()){
                bind.registerMailText.error = "Error: Enter Your Mail!"
                bind.registerMailText.requestFocus()
                return@setOnClickListener
            }
            if(pass.isEmpty()){
                bind.registerPassText.error = "Error: Enter Your Password!"
                bind.registerPassText.requestFocus()
                return@setOnClickListener
            }

            val dataService = service.userRegister(name,surname,phone,email,pass)
            dataService.enqueue(object : Callback<UserRegister>{
                override fun onResponse(
                    call: Call<UserRegister>,
                    response: Response<UserRegister>
                ) {
                    if(response.isSuccessful){

                    val u = response.body()
                    val durum = u?.user?.get(0)?.durum
                    val mesaj = u?.user?.get(0)?.mesaj

                    if(durum == true){
                        UserData.customerId =  u?.user?.get(0)?.userId
                        Toast.makeText(this@RegisterActivity, "Registration Successful", Toast.LENGTH_SHORT).show()
                        val i = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(i)

                    }else{
                        Snackbar.make(bind.root, "Result: " + mesaj, Snackbar.LENGTH_SHORT).show()
                    }
                    }
                }

                override fun onFailure(call: Call<UserRegister>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }



        bind.registerToLoginText.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
    }
}