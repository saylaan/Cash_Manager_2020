package com.cash_manager_app.utils

import android.app.Application
import java.util.*

class User_Data : Application() {

    lateinit var datas: TreeMap<String, String>
    lateinit var actualUseremail: String
    lateinit var actualUserpassword: String

    override fun onCreate() {
        super.onCreate()
        //instance = this
    }

    fun isAlreadyExist(email: String, password: String) : Boolean {
        return email in datas
    }

    fun setNewData(email: String, password: String) {
        datas[email] = password
    }

    fun verifyLog(email: String, password: String): Boolean {
        if(email in datas){
            return this.datas[email]==password
        }
        return false
    }

    fun setemail(email: String){
        this.actualUseremail = email
    }

    fun getemail(): String {
        return this.actualUseremail
    }

    fun setpassword(password: String) {
        this.actualUserpassword = password
    }

    fun getpassword(): String {
        return this.actualUserpassword
    }

    companion object {
        var instance: User_Data = User_Data()
            private set
    }

}