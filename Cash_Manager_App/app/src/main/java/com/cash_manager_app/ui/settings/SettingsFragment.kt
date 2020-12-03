package com.cash_manager_app.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cash_manager_app.R
import com.cash_manager_app.utils.User_Data

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
                ViewModelProvider(this).get(SettingsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        /*val textView: TextView = root.findViewById(R.id.text_notifications)
        settingsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/

        var actual_email: TextView = root.findViewById(R.id.affichage_actual_email)
        var actual_password: TextView = root.findViewById(R.id.affichage_actual_password)
        var modify_button: Button = root.findViewById(R.id.modify_button)
        var new_email: EditText = root.findViewById(R.id.edit_email)
        var new_password: EditText = root.findViewById(R.id.edit_password)

        actual_email.text = User_Data.instance.getemail()
        actual_password.text = User_Data.instance.getpassword()

        val clickListenerModify = View.OnClickListener {
            if(new_email.text.toString() != "") {
                User_Data.instance.setemail(new_email.text.toString())
            }
            if(new_password.text.toString() != "") {
                User_Data.instance.setpassword(new_password.text.toString())
            }
            new_email.text.clear()
            new_password.text.clear()
            actual_email.text = User_Data.instance.getemail()
            actual_password.text = User_Data.instance.getpassword()
        }
        modify_button.setOnClickListener(clickListenerModify)

        return root
    }
}