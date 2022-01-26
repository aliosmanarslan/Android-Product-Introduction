package com.aliosmanarslan.aliexpress

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.get
import com.aliosmanarslan.aliexpress.adapter.ProductAdapter
import com.aliosmanarslan.aliexpress.databinding.ActivityProductBinding
import com.aliosmanarslan.aliexpress.models.Bilgiler
import com.aliosmanarslan.aliexpress.models.Image
import com.aliosmanarslan.aliexpress.models.ProductBilgiler
import com.aliosmanarslan.aliexpress.models.ProductData
import com.aliosmanarslan.aliexpress.service.Service
import com.aliosmanarslan.aliexpress.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {
    private lateinit var bind: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityProductBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val service = ApiClient.getClient().create(Service::class.java)
        val call = service.userProduct()
        call.enqueue(object : Callback<ProductData>{
            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                if(response.isSuccessful){
                    val pro = response.body()
                    if(pro != null ){
                        val degisken = pro.products!!.get(0)!!.bilgiler!! as ArrayList<ProductBilgiler>
                        val adapter = ProductAdapter(this@ProductActivity, degisken)
                        bind.productListView.adapter = adapter

                        bind.productListView.setOnItemClickListener { adapterView, view, i, l ->

                            UserData.productId = degisken.get(i).productId
                            val productId = degisken.get(i).productId
                            val title = degisken.get(i).productName
                            val price = degisken.get(i).price
                            val description = degisken.get(i).description
                            val image = degisken.get(i).images?.get(0)?.normal
                            val intent = Intent(this@ProductActivity, ProductDetail::class.java)
                            intent.putExtra("title",title)
                            intent.putExtra("price",price)
                            intent.putExtra("description",description)
                            intent.putExtra("image",image)
                            intent.putExtra("productId",productId)
                            startActivity(intent)


                        }
                    }

                }
            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
