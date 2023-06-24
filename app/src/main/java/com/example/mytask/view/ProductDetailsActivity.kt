package com.example.mytask.view

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import com.example.mytask.R
import com.example.mytask.model.ProductListResponseClass
import com.example.mytask.viewmodel.ProductDetailsViewModel
import com.google.gson.Gson
import kotlin.math.roundToInt

/*
* Class to display product details
* */
class ProductDetailsActivity : AppCompatActivity() {

    private val viewModelClass: ProductDetailsViewModel by viewModels()
    lateinit var productimg: ImageView
    lateinit var star: ImageView
    lateinit var productname: TextView
    lateinit var price: TextView
    lateinit var ratingBar: RatingBar

    lateinit var produtdata: ProductListResponseClass.Product
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        productimg = findViewById(R.id.productimg)
        star = findViewById(R.id.favstar)
        productname = findViewById(R.id.product_name)
        price = findViewById(R.id.product_price)
        ratingBar = findViewById(R.id.ratingbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getData()
    }

    private fun getData() {
        val gson = Gson()
        val yourObject = gson.fromJson(
            intent.getStringExtra("key"), ProductListResponseClass.Product::class.java
        )
        produtdata = yourObject
        viewModelClass.setSelectedProduct(yourObject)


        viewModelClass.product.observe(this) {
            if (it.isfavorite) {
                star.setImageDrawable(
                    AppCompatResources.getDrawable(
                        this, R.drawable.ic_fill_star
                    )
                )
            } else {
                star.setImageDrawable(
                    AppCompatResources.getDrawable(
                        this, R.drawable.ic_star
                    )
                )
            }
            productname.text = it?.title
            price.text = it?.price?.get(0)?.value.toString()
            Glide.with(productimg.context).load(it?.imageURL).into(productimg)
            ratingBar.numStars = it.ratingCount.roundToInt()
            val drawable: Drawable = ratingBar.progressDrawable
            drawable.setColorFilter(Color.parseColor("#BAB800"), PorterDuff.Mode.SRC_ATOP)
        }

    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }
}