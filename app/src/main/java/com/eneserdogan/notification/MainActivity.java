package com.eneserdogan.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnClick;
    private NotificationManager notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClick=findViewById(R.id.btnClick);
        notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotification();

            }
        });

    }

    private void createNotification() {
        String channelId="exampleId";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {                                     //Create new channel
            NotificationChannel channel=new NotificationChannel(channelId,"Example channel",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);

        }
        //when the notification is clicked
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,new Intent(this,SecondActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,channelId)//Create notification
                .setSmallIcon(R.drawable.ic_error_black_24dp)
                .setContentTitle("Title")       //Notification title
                .setContentIntent(pendingIntent)//Set pendingIntent
                .setContentText("Content");     //Notification content

        notificationManager.notify(1,builder.build());//use notify method

    }
}
