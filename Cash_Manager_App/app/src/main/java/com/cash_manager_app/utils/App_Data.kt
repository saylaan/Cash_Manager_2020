package com.cash_manager_app.utils

import android.app.Application
import com.cash_manager_app.utils.User_Data.Companion.instance
import java.util.*

abstract class App_Data : Application(){

    var totalPrice: Int = 0
    var listeArticles = TreeMap<String, Float>()

    override fun onCreate() {
        super.onCreate()
        instance = this
        this.listeArticles["Pomme"]=1.5f
        this.listeArticles["Orange"]=1.5f
        this.listeArticles["Pamplemousse"]=4.3f
        this.listeArticles["Cerise"]=0.6f
        this.listeArticles["Poire"]=1.2f
        this.listeArticles["Peche"]=0.9f
        this.listeArticles["Abricot"]=0.7f
    }

    fun addTotalPrice(value: Int){
        this.totalPrice += value
    }

    fun gettotalPrice() : Int {
        return totalPrice
    }

    fun getlisteArticlesKeys() : MutableList<String> {
        val liste: MutableList<String> = ArrayList()
        for ((key, value) in listeArticles) {
            liste.add(key)
        }
        return liste
    }

    fun getlisteArticlesValues() : MutableList<String> {
        val liste: MutableList<String> = ArrayList()
        for ((key, value) in listeArticles) {
            liste.add(value.toString())
        }
        return liste
    }

    fun getlisteArticlesToListView() : List<Article> {
        val liste = ArrayList<Article>()
        for ((key, value) in listeArticles) {
            liste += Article(key, value.toString())
        }
        return liste
    }

    //fun addArticle

    companion object {

        var instance: App_Data? = null
            private set
    }

}