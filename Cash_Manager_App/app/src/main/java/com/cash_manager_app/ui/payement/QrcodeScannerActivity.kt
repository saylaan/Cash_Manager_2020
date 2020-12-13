package com.cash_manager_app.ui.payement

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import com.cash_manager_app.MainActivity
import com.cash_manager_app.R
import com.cash_manager_app.models.ServerResponse
import com.cash_manager_app.services.RetrofitClient
import com.cash_manager_app.utils.App_Data
import com.cash_manager_app.utils.User_Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val CAMERA_REQUEST_CODE = 101

class QrcodeScannerActivity : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode_scanner)

        setupPermissions()
        codeScanner()

        val buttonPayement: Button = findViewById(R.id.payement)

        buttonPayement.setOnClickListener {
            Log.e("Payement","demandé")
            RetrofitClient.instance.payement(User_Data.instance.getemail(),App_Data.instance.gettotalPrice(),123456789,1234)
                .enqueue(object : Callback<ServerResponse> {
                    override fun onResponse(call: Call<ServerResponse>, response: Response<ServerResponse>) {

                        val error = response.body()

                        Toast.makeText(applicationContext,"Pending Authorization", Toast.LENGTH_LONG)

                        if (error != null) {
                            if (error.response) {
                                Toast.makeText(applicationContext,"Payement accepted", Toast.LENGTH_SHORT)
                                startActivity(Intent(applicationContext,MainActivity::class.java))
                            }
                            else{
                                Toast.makeText(applicationContext,"Payement refused", Toast.LENGTH_SHORT)
                            }
                        }
                    }

                    override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                    }
                })
        }

    }

    fun codeScanner(){
        var qr_scanner: CodeScannerView = findViewById(R.id.qr_scanner)
        codeScanner = CodeScanner(this, qr_scanner)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    var text : TextView = findViewById(R.id.text_view)
                    text.text = it.text
                }
            }
            errorCallback = ErrorCallback {
                runOnUiThread {
                    Log.e("Main", "camera init error: ${it.message}")
                }
            }
        }

        qr_scanner.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    fun setupPermissions(){
        val permission: Int = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.CAMERA)

        if(permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }

    fun makeRequest(){
        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.CAMERA),CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"You need the camera permission to use the scan payement", Toast.LENGTH_SHORT)
                }else{
                    //success
                }
            }
        }
    }

}