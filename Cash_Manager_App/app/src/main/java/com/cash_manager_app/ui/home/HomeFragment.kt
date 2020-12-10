package com.cash_manager_app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cash_manager_app.R
import com.cash_manager_app.models.Article
import com.cash_manager_app.services.RetrofitClient
import com.cash_manager_app.utils.App_Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)


        val viewArticles: RecyclerView = root.findViewById(R.id.listeArticles)

        viewArticles.layoutManager = LinearLayoutManager(this.requireContext())
        val dataAdapter = ArticlesAdapter()
        viewArticles.adapter = dataAdapter
/*
        val liste = RetrofitClient.instance.listeArticles()

        liste.enqueue(object : Callback<List<Article>> {
            override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
                val allArticle = response.body()

                if (allArticle != null) {
                    dataAdapter.submitList(allArticle)
                }
            }
            override fun onFailure(call: Call<List<Article>>, t: Throwable) {
            }
        })
*/
        val data = App_Data.createListeArticle()
        dataAdapter.submitList(data)

        return root
    }
}