package com.example.secondcognizantapp;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.secondcognizantapp.databinding.ActivityAnyBinding;

public class AnyActivity extends AppCompatActivity {
    //Button notifyButton;

    private ActivityAnyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnyBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //notifyButton = findViewById(R.id.btnNotify);

    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });
        binding.btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }

    private void openMainActivity() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long triggerTime = System.currentTimeMillis()+30*60;
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_IMMUTABLE);
        alarmManager.set(AlarmManager.RTC, triggerTime, pendingIntent);
    }

    private void showNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        createNotificationChannel();

        // creating snooze button action for notification bar
        // its a broadcast receiver
        String ACTION_SNOOZE = "snooze";
        Intent snoozeIntent = new Intent(this, SnoozeReceiver.class);
        snoozeIntent.setAction(ACTION_SNOOZE);
        snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(this, 0, snoozeIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.baseline_add_alert_24)
                .setContentTitle("title alert notification")
                .setContentText("alert content")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                // snooze button is called like this
                .addAction(R.drawable.baseline_snooze_24, "Snooze",
                        snoozePendingIntent);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.notify(321,builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "uber channel name promotions";
            String description = "channel description --- promotions";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this.
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}