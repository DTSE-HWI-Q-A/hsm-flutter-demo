package com.vsm.flutterhsmdemoapp

import android.content.Context
import android.text.TextUtils
import android.util.Log
import androidx.annotation.NonNull
import com.huawei.agconnect.config.AGConnectServicesConfig
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.common.ApiException
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant


class MainActivity : FlutterActivity() {
    private val TAG = this::class.java.simpleName
    private val CHANNEL = "demo.gawkat.com/info"
    private lateinit var mContext: Context

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)
        mContext = this
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
                .setMethodCallHandler { call: MethodCall?, result: MethodChannel.Result? ->
                    //val arguments: Map<String, Any> = call?.arguments()
                    if (call?.method.equals("getMessage")) {
                        getAAID()
                        Thread(Runnable {
                            var appId = AGConnectServicesConfig.fromContext(mContext).getString("client/app_id");
                            try {
                                val token = HmsInstanceId.getInstance(mContext).getToken(appId, "HCM")
                                runOnUiThread {
                                    if (!TextUtils.isEmpty(token)) {
                                        Log.i(TAG, "token is $token")
                                        result?.success(token)
                                    } else {
                                        result?.success("Sin token")
                                        //result?.error("UNAVAILABLE", "push not available.", null)
                                    }
                                }
                            } catch (e: ApiException) {
                                e.printStackTrace()
                                Log.e(TAG, e.message)
                            }
                        }).start()
                        //result?.success(message);
                        //openWebPage("https://flutter.io");
                    }
                }
    }


    private fun getAAID() {
        val aaidResultTask = HmsInstanceId.getInstance(mContext).aaid
        aaidResultTask.addOnSuccessListener { aaidResult ->
            val aaid = aaidResult.id
            Log.i("MainActivity", "aaid is $aaid")
        }.addOnFailureListener { Log.i("MainActivity", "getAAID failed") }
    }
}
