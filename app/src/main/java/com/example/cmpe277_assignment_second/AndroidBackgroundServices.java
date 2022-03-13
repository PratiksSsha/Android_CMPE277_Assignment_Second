package com.example.cmpe277_assignment_second;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;

public class AndroidBackgroundServices extends Service {

    private String TAGNAME = "Andorid Background Service";
    NotificationManagerCompat notificationManager;

    NotificationCompat.Builder builder;
        @Override
        public int onStartCommand(Intent intent, int flags, int startId)
        {
            Log.e("Service", "Android background service started" );
            Bundle extras = intent.getExtras();

            ArrayList<String> from = (ArrayList<String>) extras.get("allFiles");

            Log.e("Service", from + "aa");
            for (String fileName:from )
            {

            new AndoridFileDownloading().execute(fileName);

            }
        NotificationChannel();

        builder = new NotificationCompat.Builder(this, "105");
        builder.setSmallIcon(R.drawable.icdownload)
                .setContentTitle("Andorid file download")
                .setContentText("Android file downloading in Progress")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        notificationManager = NotificationManagerCompat.from(getApplicationContext());


        notificationManager.notify(1008, builder.build());


        stopSelf();
        return super.onStartCommand(intent, flags, startId);

        }
        private void NotificationChannel()
        {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel ch = new NotificationChannel("105", "105", importance);


            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(ch);
        }
    }
    @Override
    public void onDestroy()
    {

        Log.e(TAGNAME, "on Destroy");
        builder.setContentText("Android file is downloaded");
        notificationManager.notify(1008, builder.build());

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {

           return null;
    }



    }
