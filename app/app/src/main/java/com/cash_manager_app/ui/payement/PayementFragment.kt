package com.cash_manager_app.ui.payement

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cash_manager_app.R
import com.cash_manager_app.utils.App_Data

class PayementFragment : Fragment() {

    private lateinit var payementViewModel: PayementViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        payementViewModel =
                ViewModelProvider(this).get(PayementViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_payement, container, false)


        var prixTotal: TextView = root.findViewById(R.id.affichage_prix_total)
        prixTotal.text = String.format("%.2f", App_Data.instance.gettotalPrice())

        var buttonPayement: Button = root.findViewById(R.id.payement_scan)

        buttonPayement.setOnClickListener {
            if(App_Data.instance.getisConnected()) {
                startActivity(Intent(this.context, QrcodeScannerActivity::class.java))
            }else{
                Toast.makeText(this.context,"Vous devez vous connecter au serveur avant", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }
}