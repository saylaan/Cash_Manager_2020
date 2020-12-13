package com.cash_manager_app.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.cash_manager_app.MainActivity
import com.cash_manager_app.R
import com.cash_manager_app.utils.Constants
import com.cash_manager_app.utils.User_Data

class Login : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txtUsername = findViewById<EditText>(R.id.username)
        val txtPassword = findViewById<EditText>(R.id.password)

        val signin = findViewById<Button>(R.id.signin)
        val register = findViewById<Button>(R.id.register)

        val clickListenerRegister = View.OnClickListener {
            // Register

            User_Data.instance.setNewData(txtUsername.text.toString(),txtPassword.text.toString())
            User_Data.instance.setemail(txtUsername.text.toString())
            User_Data.instance.setpassword(txtPassword.text.toString())

            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)

        }

        val clickListenerSignin = View.OnClickListener {
            // Sign in

            if((txtUsername.text.toString() == Constants().USEREMAIL)&&(txtPassword.text.toString() == Constants().USERPASSWORD)){
                User_Data.instance.setemail(txtUsername.text.toString())
                User_Data.instance.setpassword(txtPassword.text.toString())
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }else{
                /*val mAlertDialog = AlertDialog.Builder(applicationContext)
                mAlertDialog.setMessage("Mauvais identifiants")
                mAlertDialog.show()
                */
            }
        }

        signin.setOnClickListener(clickListenerSignin)
        register.setOnClickListener(clickListenerRegister)

    }




}