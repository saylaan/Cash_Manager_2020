package com.cash_manager_app.services

import com.cash_manager_app.models.Article
import com.cash_manager_app.models.Payement
import com.cash_manager_app.models.ServerResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("/store")
    fun listeArticles() : Call<List<Article>>

    @FormUrlEncoded
    @POST("/connect")
    fun connectServer(
            @Field("name") name: String,
            @Field("password") password: String
    ) : Call<ServerResponse>

    @FormUrlEncoded
    @POST("/new-server-password")
    fun modifyPassword(
        @Field("password") password: String
    ) : Call<ServerResponse>

    @FormUrlEncoded
    @POST("/payment")
    fun payement(
            @Field("username") username: String,
            @Field("amount") amount: Double,
            @Field("BIC") bic: Int,
            @Field("number") number: Int
            //@Field("payementMethod") payementMethod: String
    ) : Call<ServerResponse>
}