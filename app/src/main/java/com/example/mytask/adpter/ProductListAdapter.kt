package com.example.mytask.adpter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mytask.R
import com.example.mytask.model.ProductListResponseClass
import com.example.mytask.viewmodel.ProductDetailsViewModel

/*
* Class Description: Adapter class to show products
* */
class ProductListAdapter(
    private var data: ProductListResponseClass,
    private var mList: List<ProductListResponseClass.Product>?,
    private val listtner: onItemClick,
    private val context: Context
) : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_list_adapter, parent, false)
        return ProductListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val ItemsViewModel = data.products[position]
        holder.productname.text = ItemsViewModel.title
        holder.price.text = ItemsViewModel.price[0].value.toString()
        Glide.with(holder.product_image.context).load(ItemsViewModel?.imageURL)
            .into(holder.product_image)
        if (ItemsViewModel.isfavorite) {
            holder.favorite.setImageDrawable(getDrawable(context, R.drawable.ic_fill_star))
        } else {
            holder.favorite.setImageDrawable(getDrawable(context, R.drawable.ic_star))
        }
        holder.favorite.setOnClickListener {

            holder.favorite.setImageDrawable(getDrawable(context, R.drawable.ic_fill_star))
            ItemsViewModel.isfavorite = true
            listtner.onFavoriteClick(ItemsViewModel, data)
        }
        holder.itemView.setOnClickListener {
            listtner.onClickItem(ItemsViewModel)
        }
        if (ItemsViewModel.isAddToCartEnable) {
            holder.addtocart.visibility = View.VISIBLE
            holder.addtocart.text = ItemsViewModel.addToCartButtonText
        } else {
            holder.addtocart.visibility = View.GONE
        }
    }

    class ProductListViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val product_image: ImageView = itemview.findViewById(R.id.product_image)
        val favorite: ImageView = itemview.findViewById(R.id.favorite)
        val productname: TextView = itemview.findViewById(R.id.productname)
        val addtocart: Button = itemview.findViewById(R.id.addtocart)
        val price: TextView = itemview.findViewById(R.id.price)

    }

    interface onItemClick {
        fun onClickItem(userdate: ProductListResponseClass.Product?)
        fun onFavoriteClick(
            userdate: ProductListResponseClass.Product?,
            mList: ProductListResponseClass
        )
    }

}