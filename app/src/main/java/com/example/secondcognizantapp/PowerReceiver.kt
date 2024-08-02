package com.example.secondcognizantapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class PowerReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val intentAction = intent.action
        var toastMessage : String? = null


        when (intentAction) {
            Intent.ACTION_POWER_CONNECTED -> {
                toastMessage = "Power connected"
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                toastMessage = "Power disconnected"
            }
        }

        Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show();
    }
}