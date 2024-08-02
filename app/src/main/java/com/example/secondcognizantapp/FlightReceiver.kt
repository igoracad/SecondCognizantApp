package com.example.secondcognizantapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class FlightReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.i("Flight", "Received a flight broadcast")
    }

}