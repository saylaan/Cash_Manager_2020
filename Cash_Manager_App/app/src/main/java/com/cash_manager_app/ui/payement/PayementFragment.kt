package com.cash_manager_app.ui.payement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
        /*val textView: TextView = root.findViewById(R.id.text_payement)
        payementViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/

        var prixTotal: TextView = root.findViewById(R.id.affichage_prix_total)
        prixTotal.text = App_Data.instance?.gettotalPrice().toString()

        var buttonCard: Button = root.findViewById(R.id.payement_card)
        var buttonCheque: Button = root.findViewById(R.id.payement_cheque)

        
        return root
    }
}