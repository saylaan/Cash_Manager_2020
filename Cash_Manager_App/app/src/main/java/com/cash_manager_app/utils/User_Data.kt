package com.cash_manager_app.utils

import android.app.Application
import java.util.*

class User_Data : Application() {

    var datas = TreeMap<String, String>()

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun setNewData(email: String, password: String){
        datas[email] = password
    }

    fun verifyLog(email: String, password: String): Boolean? {
        if(email in datas){
            return datas[email]==password
        }
        return false
    }

    companion object {
        var instance: User_Data? = null
            private set
    }

}