package com.example.secondcognizantapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Snooze extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("SnoozeBTN", "snooze button was clicked");
    }
}