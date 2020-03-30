package com.vsm.flutterhsmdemoapp

import android.content.Intent
import android.net.Uri
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel


class MainActivity : FlutterActivity() {
    private val CHANNEL = "demo.gawkat.com/info"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        //GeneratedPluginRegistrant.registerWith(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
                .setMethodCallHandler { call: MethodCall?, result: MethodChannel.Result? ->
                    //val arguments: Map<String, Any> = call?.arguments()
                    if (call?.method.equals("getMessage")) {
//                    String from = (String) arguments.get("from");
                        val message = "Android say hi "
                        result?.success(message);
                        //openWebPage("https://flutter.io");
                    }

                }
    }

    /*fun openWebPage(url: String?) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }*/
}
