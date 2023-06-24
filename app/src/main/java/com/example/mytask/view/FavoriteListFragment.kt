package com.example.mytask.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytask.R
import com.example.mytask.adpter.FavoriteListAdapter
import com.example.mytask.model.ProductListResponseClass
import com.example.mytask.utils.SharedPrefUtil
import com.example.mytask.viewmodel.FavoritesViewModel
import com.example.mytask.viewmodel.ProductDetailsViewModel
import com.example.mytask.viewmodel.ProductListViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
/*
* Fragment class to display Favourite products list
* */
class FavoriteListFragment : Fragment(), FavoriteListAdapter.onItemClick {
    lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavoriteListAdapter
    private val viewModelClass: FavoritesViewModel by viewModels()
    private val detailsViewModel: ProductDetailsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_list, container, false)
        recyclerView = view.findViewById(R.id.favorite_list)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val userdata = SharedPrefUtil.getFevProduct(requireContext())
        val gson = Gson()
        val userdetail = gson.fromJson(userdata, ProductListResponseClass::class.java)
        viewModelClass.getFavoriteList(userdetail)


        viewModelClass.favoriteProducts.observe(viewLifecycleOwner) { favoriteProducts ->
            if (favoriteProducts != null) {
                adapter = FavoriteListAdapter(userdetail, favoriteProducts, this, requireContext())
                recyclerView.adapter = adapter
            }
        }
        return view
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
        viewModelClass.removeFavorite(userdate)

    }


}