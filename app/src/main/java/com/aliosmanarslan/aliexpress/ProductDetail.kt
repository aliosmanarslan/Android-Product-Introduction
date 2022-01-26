package com.aliosmanarslan.aliexpress

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aliosmanarslan.aliexpress.databinding.ActivityProductDetailBinding
import com.aliosmanarslan.aliexpress.models.ProductBilgiler
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import android.R
import android.graphics.drawable.Drawable
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.aliosmanarslan.aliexpress.models.OrderData
import com.aliosmanarslan.aliexpress.service.Service
import com.aliosmanarslan.aliexpress.utils.ApiClient
import com.bumptech.glide.request.target.Target
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductDetail : AppCompatActivity() {
    private lateinit var bind: ActivityProductDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(bind.root)


        val title = intent.getStringExtra("title")
        bind.proDetailTitle.text = title

        val price = intent.getStringExtra("price")
        bind.proDetailPrice.text = price + "₺"

        val description = intent.getStringExtra("description")
        bind.proDetailDescription.text = description

        //val customerId = intent.getStringExtra("customerId")
        //val productId = intent.getStringExtra("productId")

        val image = intent.getStringExtra("image")
        //bind.proDetailImg.setImageResource(bind.proDetailImg)
        //Picasso.get().load(image).into(bind.proDetailImg)
        Glide.with(this@ProductDetail).load(image).into(bind.proDetailImg);

        val service = ApiClient.getClient().create(Service::class.java)
        val call = service.orderData(customerId = UserData.customerId!!, productId = UserData.productId!!)

        call.enqueue(object : Callback<OrderData>{
            override fun onResponse(call: Call<OrderData>, response: Response<OrderData>) {
                bind.proDetailBtn.setOnClickListener {
                    if (response.isSuccessful){
                        val pro = response.body()

                        if(pro != null){
                            var durum = pro.order?.get(0)?.durum
                            var mesaj = pro.order?.get(0)?.mesaj
                            if(durum == true){
                                Toast.makeText(this@ProductDetail, "Status : " + mesaj, Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(this@ProductDetail, "Status : " + mesaj, Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
                }
            }

            override fun onFailure(call: Call<OrderData>, t: Throwable) {

            }

        })
    }
}