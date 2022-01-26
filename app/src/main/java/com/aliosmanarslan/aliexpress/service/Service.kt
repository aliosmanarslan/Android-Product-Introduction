package com.aliosmanarslan.aliexpress.service


import com.aliosmanarslan.aliexpress.models.OrderData
import com.aliosmanarslan.aliexpress.models.ProductData
import com.aliosmanarslan.aliexpress.models.UserLogin
import com.aliosmanarslan.aliexpress.models.UserRegister
import com.aliosmanarslan.aliexpress.utils.ApiClient
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("userLogin.php")
    fun userLogin(
        @Query("userEmail") userEmail:String,
        @Query("userPass") userPass:String,
        @Query("ref") ref:String = ApiClient.ref,
        @Query("face") face:String = "no",
    ) : Call<UserLogin>

    @GET("userRegister.php")
    fun userRegister(
        @Query("userName") userName:String,
        @Query("userSurname") userSurname:String,
        @Query("userPhone") userPhone:String,
        @Query("userMail") userMail:String,
        @Query("userPass") userPass:String,
        @Query("ref") ref:String = ApiClient.ref,
    ) : Call<UserRegister>

    @GET("product.php")
    fun userProduct(
        @Query("ref") ref:String = ApiClient.ref,
        @Query("start") start:String = "0",
    ) : Call<ProductData>


    @GET("orderForm.php")
    fun orderData(
        @Query("ref") ref:String = ApiClient.ref,
        @Query("customerId") customerId:String? = null,
        @Query("productId") productId:String? = null,
        @Query("html") html:String = "12",
    ) : Call<OrderData>
}