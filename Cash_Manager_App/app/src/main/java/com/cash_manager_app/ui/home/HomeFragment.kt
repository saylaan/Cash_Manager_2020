package com.cash_manager_app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cash_manager_app.R
import com.cash_manager_app.utils.App_Data

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
        //val textView: TextView = root.findViewById(R.id.text_home)
        /*homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        var viewArticles: RecyclerView = root.findViewById(R.id.listeArticles)

        //var listeArticlesKeys = App_Data.instance!!.getlisteArticlesKeys()
        //var listeArticlesValues = App_Data.instance!!.getlisteArticlesValues()
        var listeArticles = App_Data.instance?.getlisteArticlesToListView()

        var dataAdapter = ArticlesAdapter(listeArticles)
        viewArticles.adapter = dataAdapter
        viewArticles.layoutManager = LinearLayoutManager(this.requireActivity())






        return root
    }
}