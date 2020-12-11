package com.cash_manager_app.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cash_manager_app.MainActivityConnected
import com.cash_manager_app.R
import com.cash_manager_app.models.ServerResponse
import com.cash_manager_app.services.RetrofitClient
import com.cash_manager_app.utils.App_Data
import com.cash_manager_app.utils.User_Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class SettingsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        var isConnected = App_Data.instance.getisConnected() //permet de savoir si l'appli est connectée au serveur

        //if(!isConnected) {

            val root = inflater.inflate(R.layout.fragment_settings, container, false)

            val actual_email: TextView = root.findViewById(R.id.affichage_actual_email)
            val actual_password: TextView = root.findViewById(R.id.affichage_actual_password)
            val modify_button: Button = root.findViewById(R.id.modify_button)
            val new_email: EditText = root.findViewById(R.id.edit_email)
            val new_password: EditText = root.findViewById(R.id.edit_password)
            val serverNameConnect: EditText = root.findViewById(R.id.server_name)
            val serverPasswordConnect: EditText = root.findViewById(R.id.server_password)
            val connectServer : Button = root.findViewById(R.id.connect_server_button)



            actual_email.text = User_Data.instance.getemail()
            actual_password.text = User_Data.instance.getpassword()

            /** fonction pour le bouton pour se connecter au serveur */
            val clickListenerConnect = View.OnClickListener {

                /** appel du POST pour demander la connexion au serveur */

                if(serverNameConnect.text.isEmpty() || serverPasswordConnect.text.isEmpty()){
                    Toast.makeText(this.context, "Vous devez renseigner tous les champs !", Toast.LENGTH_SHORT).show();
                }else {/*
                    RetrofitClient.instance.connectServer(serverNameConnect.text.toString(), serverPasswordConnect.text.toString())
                            .enqueue(object : Callback<ServerResponse> {
                                override fun onResponse(call: Call<ServerResponse>, response: Response<ServerResponse>) {

                                    val error = response.body()
                                    if (error != null) {
                                        if (error.response) {
                                            App_Data.instance.setisConnected(true)
                                            User_Data.instance.setserverName(serverNameConnect.text.toString())
                                            User_Data.instance.setserverPassword(serverPasswordConnect.text.toString())
                                        }
                                    }
                                }

                                override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                                }
                            })

                */
                    App_Data.instance.setisConnected(true)
                    startActivity(Intent(this.context, MainActivityConnected::class.java))
                }
                /*val parent_fragment = getActivity()?.supportFragmentManager?.beginTransaction()
                val new_fragment = SettingsFragment()
                if (parent_fragment != null) {
                    parent_fragment.replace(R.id.container, new_fragment)
                    parent_fragment.commit()
                }*/

            }

            /** fonction pour le bouton pour modifier les identifiants User */
            val clickListenerModify = View.OnClickListener {
                if (new_email.text.toString() != "") {
                    User_Data.instance.setemail(new_email.text.toString())
                }
                if (new_password.text.toString() != "") {
                    User_Data.instance.setpassword(new_password.text.toString())
                }
                new_email.text.clear()
                new_password.text.clear()
                actual_email.text = User_Data.instance.getemail()
                actual_password.text = User_Data.instance.getpassword()
            }
            modify_button.setOnClickListener(clickListenerModify)
            connectServer.setOnClickListener(clickListenerConnect)

            return root
        //}
        /*
        else{
            val root = inflater.inflate(R.layout.fragment_settings_connected, container, false)

            val actual_email: TextView = root.findViewById(R.id.affichage_actual_email)
            val actual_password: TextView = root.findViewById(R.id.affichage_actual_password)
            val modify_button: Button = root.findViewById(R.id.modify_button)
            val new_email: EditText = root.findViewById(R.id.edit_email)
            val new_password: EditText = root.findViewById(R.id.edit_password)
            val serverName: TextView = root.findViewById(R.id.name_server)
            val serverPassword: TextView = root.findViewById(R.id.password_server)
            val serverNewPassword: EditText = root.findViewById(R.id.new_password_server)
            val password_server_modify : Button = root.findViewById(R.id.modify_server_button)
            val disconnectServer : Button = root.findViewById(R.id.disconnect_server_button)

            actual_email.text = User_Data.instance.getemail()
            actual_password.text = User_Data.instance.getpassword()
            //serverName.text = User_Data.instance.getserverName()
            //serverPassword.text = User_Data.instance.getserverPassword()
/*
            /** fonction pour le bouton pour modifier le mot du passe du serveur */
            val clickListenerModifiyServerPassword = View.OnClickListener {

                RetrofitClient.instance.modifyPassword(serverNewPassword.text.toString())
                        .enqueue(object : Callback<ServerResponse> {
                            override fun onResponse(call: Call<ServerResponse>, response: Response<ServerResponse>) {
                                val error = response.body()

                                if (error != null) {
                                    if(error.response){
                                        User_Data.instance.setserverPassword(serverNewPassword.text.toString())
                                    }
                                }
                            }
                            override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                            }
                        })

            }

 */
            /** fonction pour le bouton pour se deconnecter du serveur */
            val clickListenerDisconnect = View.OnClickListener {


                App_Data.instance.setisConnected(false)
                /*
                val parent_fragment = getActivity()?.supportFragmentManager?.beginTransaction()
                val new_fragment = SettingsFragment()
                if (parent_fragment != null) {
                    parent_fragment.replace(R.id.container, new_fragment)
                    parent_fragment.commit()
                }*/
            }

            /** fonction pour le bouton pour modifier les identifiants User */
            val clickListenerModify = View.OnClickListener {
                if (new_email.text.toString() != "") {
                    User_Data.instance.setemail(new_email.text.toString())
                }
                if (new_password.text.toString() != "") {
                    User_Data.instance.setpassword(new_password.text.toString())
                }
                new_email.text.clear()
                new_password.text.clear()
                actual_email.text = User_Data.instance.getemail()
                actual_password.text = User_Data.instance.getpassword()
            }

            modify_button.setOnClickListener(clickListenerModify)
            disconnectServer.setOnClickListener(clickListenerDisconnect)

            return root
        }*/
    }
}