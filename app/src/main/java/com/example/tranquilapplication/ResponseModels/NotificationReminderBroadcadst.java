package com.example.tranquilapplication.ResponseModels;

import static android.content.Context.MODE_PRIVATE;
import static com.example.tranquilapplication.Services.Constants.KEY_BOOKED_DATE;
import static com.example.tranquilapplication.Services.Constants.KEY_DOC_ID;
import static com.example.tranquilapplication.Services.Constants.KEY_NAME;
import static com.example.tranquilapplication.Services.Constants.PREFERENCE_NAME;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.tranquilapplication.MainActivities.BookDoctorActivity;
import com.example.tranquilapplication.MainActivities.MainMenuActivity;
import com.example.tranquilapplication.R;

public class NotificationReminderBroadcadst extends BroadcastReceiver {

    SharedPreferences sharedPreferences;
    public void onReceive(Context context, Intent intent) {

        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);



        Intent i = new Intent(context, BookDoctorActivity.class);
                PendingIntent pi = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_MUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifypatient")
                .setSmallIcon(R.drawable.baseline_add_alert_24)
                .setContentTitle("Hi "+ name)
                .setStyle(new NotificationCompat.BigTextStyle()
                .bigText("Make sure to take the screening test again in anther two weeks!"))
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pi)   ;
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123, builder.build());

    }




}
