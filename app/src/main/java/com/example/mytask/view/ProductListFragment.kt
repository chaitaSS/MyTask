package com.example.mytask.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytask.R
import com.example.mytask.adpter.ProductListAdapter
import com.example.mytask.model.ProductListResponseClass
import com.example.mytask.utils.SharedPrefUtil
import com.example.mytask.viewmodel.ProductDetailsViewModel
import com.example.mytask.viewmodel.ProductListViewModel
import com.google.gson.Gson

/*
* Fragment class to display all products list
* */
class ProductListFragment : Fragment(), ProductListAdapter.onItemClick {
    lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductListAdapter
    private val viewModelClass: ProductListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)
        recyclerView = view.findViewById(R.id.productlist)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        getProductList()
        return view
    }

    private fun getProductList() {
        viewModelClass.products.observe(requireActivity()) {
            if (it != null) {
                adapter = ProductListAdapter(it, it.products, this, requireContext())
                val gson = Gson()
                val jsonString = gson.toJson(it)
                SharedPrefUtil.setUserData(requireContext(), jsonString)
                recyclerView.adapter = adapter
            }
        }
    }

    override fun onClickItem(userdate: ProductListResponseClass.Product?) {
        val gson = Gson()
        val intent = Intent(requireActivity(), ProductDetailsActivity::class.java)
        intent.putExtra("key", gson.toJson(userdate))
        startActivity(intent)
    }

    override fun onFavoriteClick(
        userdate: ProductListResponseClass.Product?, mList: ProductListResponseClass
    ) {
        val gson = Gson()
        val jsonString = gson.toJson(mList)
        SharedPrefUtil.setFevProduct(requireContext(), jsonString)
    }

}