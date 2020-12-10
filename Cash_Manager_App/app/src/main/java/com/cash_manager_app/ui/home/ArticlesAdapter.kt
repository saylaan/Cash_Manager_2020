package com.cash_manager_app.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cash_manager_app.R
import com.cash_manager_app.utils.App_Data
import com.cash_manager_app.models.Article

class ArticlesAdapter
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listeArticles: List<Article> = ArrayList()

    class ArticleViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.article)
        val prix: TextView = itemView.findViewById(R.id.price)
        val article_count: TextView = itemView.findViewById(R.id.article_count)
        val ajouter: Button = itemView.findViewById(R.id.ajouter)
        var supprimer: Button = itemView.findViewById((R.id.supprimer))

        fun bind(article: Article, position: Int){
            name.text = article.article
            prix.text = article.price.toString()
            article_count.text = App_Data.instance.getCount(position).toString()
            val clickListenerAjouter = View.OnClickListener {
                //Ajouter un article et ajouter son prix au prix total
                App_Data.instance.addTotalPrice(article.price)
                App_Data.instance.addCount(position)
                article_count.text = App_Data.instance.getCount(position).toString()
            }
            val clickListenerSupprimer = View.OnClickListener {
                //Supprimer un article et soustraire son prix au prix total
                if(App_Data.instance.getCount(position) > 0){
                    App_Data.instance.minusTotalPrice(article.price)
                    App_Data.instance.minusCount(position)
                    article_count.text = App_Data.instance.getCount(position).toString()
                }
            }
            ajouter.setOnClickListener(clickListenerAjouter)
            supprimer.setOnClickListener(clickListenerSupprimer)
        }
    }

    fun submitList(liste: List<Article>){
        listeArticles = liste
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listview_item, parent, false)

        return ArticleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ArticleViewHolder ->{
                val currentArticle = listeArticles.get(position)
                holder.bind(currentArticle, position)
            }
        }

    }

    override fun getItemCount(): Int {
        return listeArticles.size
    }

}