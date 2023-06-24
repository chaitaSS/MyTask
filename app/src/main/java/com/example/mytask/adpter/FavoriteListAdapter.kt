package com.example.mytask.adpter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mytask.R
import com.example.mytask.model.FavoriteItem
import com.example.mytask.model.ProductListResponseClass

/*
* Class Description: Adapter class to show products marked as Favorite
* */

class FavoriteListAdapter(
    private var data: ProductListResponseClass,
    private var mList: List<ProductListResponseClass.Product>?,
    private val listtner: onItemClick,
    private val context: Context
) : RecyclerView.Adapter<FavoriteListAdapter.FavoriteListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_list_adapter, parent, false)
        return FavoriteListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    override fun onBindViewHolder(holder: FavoriteListViewHolder, position: Int) {
        val ItemsViewModel = data.products[position]

        if (ItemsViewModel.isfavorite) {
            holder.favorite.setImageDrawable(
                AppCompatResources.getDrawable(
                    context, R.drawable.ic_fill_star
                )
            )
            holder.productname.text = ItemsViewModel.title
            holder.price.text = ItemsViewModel.price[0].value.toString()
            Glide.with(holder.product_image.context).load(ItemsViewModel.imageURL)
                .into(holder.product_image)
        }

        holder.itemView.setOnClickListener {
            listtner.onClickItem(ItemsViewModel)
        }
        holder.favorite.setOnClickListener {
            ItemsViewModel.isfavorite = false
            listtner.onFavoriteClick(ItemsViewModel, data)
        }
        holder.addtocart.visibility = View.GONE
    }

    class FavoriteListViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val product_image: ImageView = itemview.findViewById(R.id.product_image)
        val favorite: ImageView = itemview.findViewById(R.id.favorite)
        val productname: TextView = itemview.findViewById(R.id.productname)
        val addtocart: Button = itemview.findViewById(R.id.addtocart)
        val price: TextView = itemview.findViewById(R.id.price)

    }

    interface onItemClick {
        fun onClickItem(userdate: ProductListResponseClass.Product?)
        fun onFavoriteClick(
            userdate: ProductListResponseClass.Product?, mList: ProductListResponseClass
        )
    }

}