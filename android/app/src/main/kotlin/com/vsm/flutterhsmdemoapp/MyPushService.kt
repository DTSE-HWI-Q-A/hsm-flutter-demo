package com.vsm.flutterhsmdemoapp

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage


class MyPushService : HmsMessageService() {
    private val TAG = this::class.java.simpleName

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i(TAG, "receive token:$token")
    }

    private fun refreshedTokenToServer(token: String) {
        Log.i(TAG, "sending token to server. token:$token")
    }
}