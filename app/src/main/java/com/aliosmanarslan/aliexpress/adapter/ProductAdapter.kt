package com.aliosmanarslan.aliexpress.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.aliosmanarslan.aliexpress.databinding.ProductRowBinding.inflate
import com.aliosmanarslan.aliexpress.models.Bilgiler
import com.aliosmanarslan.aliexpress.models.ProductBilgiler
import com.bumptech.glide.Glide


class ProductAdapter(private val context: Context, private val arrayList:ArrayList<ProductBilgiler>) : BaseAdapter(){
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint("SetTextI18n")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val bind = inflate(LayoutInflater.from(context), p2, false)
        val item = arrayList.get(p0)
        bind.txtProductTitle.text = item.productName
        bind.txtProductPrice.text = item.price + "₺"
        item.images?.get(0)
        Glide.with(context).load(item.images?.get(0)?.normal).centerCrop().into(bind.productImg)


        return bind.root
    }
}