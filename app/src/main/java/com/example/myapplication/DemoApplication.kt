package com.example.myapplication

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.adobe.marketing.mobile.Identity
import com.adobe.marketing.mobile.Lifecycle
import com.adobe.marketing.mobile.LoggingMode
import com.adobe.marketing.mobile.MobileCore
import com.adobe.marketing.mobile.Signal
import io.branch.adobe.extension.AdobeBranch
import io.branch.adobe.extension.AdobeBranchExtension
import io.branch.referral.Branch
import org.json.JSONException


class DemoApplication : Application() {
    companion object {
        private const val ADOBE_APP_ID = "d10f76259195/1e684db798b4/launch-bd5714b763f7-development"
    }

    private val initListener = Branch.BranchReferralInitListener { referringParams, _ ->
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
    override fun onCreate() {
        super.onCreate()

        MobileCore.setApplication(this)
        MobileCore.configureWithAppID(ADOBE_APP_ID)
        MobileCore.setLogLevel(LoggingMode.ERROR)
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(p0: Activity, p1: Bundle?) {
            }

            override fun onActivityStarted(p0: Activity) {
                AdobeBranch.initSession(
                    initListener , p0.intent.data, p0)
            }

            override fun onActivityResumed(p0: Activity) {
            }

            override fun onActivityPaused(p0: Activity) {
            }

            override fun onActivityStopped(p0: Activity) {
            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
            }

            override fun onActivityDestroyed(p0: Activity) {
            }

        })

        Branch.enableLogging()

        val extensions = listOf(AdobeBranchExtension.EXTENSION, Identity.EXTENSION, Signal.EXTENSION, Lifecycle.EXTENSION,Lifecycle.EXTENSION)

        MobileCore.registerExtensions(extensions) {
            android.util.Log.d("Debug", "AEP Mobile SDK is initialized")
        }
    }
}