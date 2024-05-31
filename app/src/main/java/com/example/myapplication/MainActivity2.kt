package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adobe.marketing.mobile.MobileCore

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
    }

    override fun onResume() {
        super.onResume()
        //BranchEvent("TEST").logEvent(this)
        MobileCore.trackAction("TEST",null)
    }
}