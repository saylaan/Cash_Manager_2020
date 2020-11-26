package com.cash_manager_app.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cash_manager_app.MainActivity
import com.cash_manager_app.R
import com.cash_manager_app.utils.App_Data
import com.cash_manager_app.utils.Article
import com.cash_manager_app.utils.User_Data

class ArticlesAdapter (private val listeArticles : List<Article>?)
    : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val article: TextView = itemView.findViewById(R.id.article)
        val prix: TextView = itemView.findViewById(R.id.price)
        val ajouter: Button = itemView.findViewById(R.id.ajouter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listview_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentArticle = listeArticles?.get(position)

        if (currentArticle != null) {
            holder.article.text = currentArticle.article
            holder.prix.text = currentArticle.prix
        }


        val clickListenerAjouter = View.OnClickListener {
            // Ajouter le prix de l'article au prix total

            if (currentArticle != null) {
                App_Data.instance?.addTotalPrice(currentArticle.prix.toInt())
            }

        }
        holder.ajouter.setOnClickListener(clickListenerAjouter)
    }

    override fun getItemCount(): Int {
        if (listeArticles != null) {
            return listeArticles.size
        }
        return 0
    }

}