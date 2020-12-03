package com.cash_manager_app.utils

import android.app.Application
import com.cash_manager_app.ui.home.ArticlesAdapter
import com.cash_manager_app.utils.User_Data.Companion.instance
import java.util.*

class App_Data : Application(){

    var totalPrice: Double = 0.00
    private var count_per_article = Array<Int>(100){0}

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    /*fun sizeListe(value: Int) {
        count_per_article = Array(value){2}
    }*/

    fun addCount(pos: Int) {
        count_per_article[pos] += 1
    }

    fun minusCount(pos: Int) {
        count_per_article[pos] -= 1
    }

    fun getCount(pos: Int): Int {
        return count_per_article[pos]
    }

    fun addTotalPrice(value: Double){
        this.totalPrice += value
    }

    fun minusTotalPrice(value: Double){
        this.totalPrice -= value
        if(totalPrice <0){
            totalPrice = 0.0
        }
    }

    fun gettotalPrice() : Double {
        return totalPrice
    }

    /*fun getlisteArticlesKeys() : MutableList<String> {
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

        for ((key, value) in listeArticles) {
            liste += Article(key, value.toString())
        }
        return liste
    }*/

    //fun addArticle

    companion object {

        var instance: App_Data = App_Data()
            private set

        fun createListeArticle(): ArrayList<Article>{
            val liste = ArrayList<Article>()
            liste.add( Article(
                    "Abricot",
                    0.70
            ))
            liste.add( Article(
                    "Banane (100g)",
                    1.90
            ))
            liste.add( Article(
                    "Cerise",
                    0.60
            ))
            liste.add( Article(
                    "Framboise (200g)",
                    1.40
            ))
            liste.add( Article(
                    "Fruit de la passion",
                    0.60
            ))
            liste.add( Article(
                    "Kiwi",
                    0.30
            ))
            liste.add( Article(
                    "Melon",
                    0.70
            ))
            liste.add( Article(
                    "Orange",
                    1.50
            ))
            liste.add( Article(
                    "Pamplemousse",
                    4.30
            ))
            liste.add( Article(
                    "Pasteque",
                    2.80
            ))
            liste.add( Article(
                    "Peche",
                    0.90
            ))
            liste.add( Article(
                    "Poire",
                    1.20
            ))
            liste.add( Article(
                "Pomme",
                1.50
            ))
            return liste
        }
    }

}