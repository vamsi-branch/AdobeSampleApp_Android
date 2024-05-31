package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import io.branch.adobe.extension.AdobeBranch
import io.branch.referral.Branch
import org.json.JSONException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private val initListener = Branch.BranchReferralInitListener { referringParams, error ->
        try {
            // Retrieve Deep Link params and route to content appropriately
            referringParams?.let {
                if (it.has("+clicked_branch_link") && it.getBoolean("+clicked_branch_link")) {
                    // Handle your Branch Deep Link routing in the callback
                }
            }
        } catch (e: JSONException) {
            // referringParams property doesn't exist
        }
    }
    override fun onStart() {
        super.onStart()
//        AdobeBranch.initSession(
//            initListener , intent.data, this)

        val btn = findViewById<View>(R.id.button) as Button

        btn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    MainActivity2::class.java
                )
            )
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        AdobeBranch.reInitSession(this, initListener)
    }
}