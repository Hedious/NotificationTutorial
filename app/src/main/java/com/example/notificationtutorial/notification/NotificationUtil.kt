package com.example.notificationtutorial.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.example.notificationtutorial.MainActivity
import com.example.notificationtutorial.MyReceiver
import com.example.notificationtutorial.R
import com.example.notificationtutorial.navigation.MY_ARG
import com.example.notificationtutorial.navigation.MY_URI
import com.example.notificationtutorial.navigation.NotificationScreen

fun sendNotification(context: Context, body: String, title: String) {
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    //Should  use Package Name
    val NOTIFICATION_CHANNEL_ID = "com.example.notificationtutorial" + ".channel"

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = context.getString(R.string.app_name)
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            name,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
    }
    val notification = buildNotification(context, NOTIFICATION_CHANNEL_ID, title, body)
    notificationManager.notify(getUniqueId(), notification)
}

private fun buildNotification(
    context: Context,
    channelId: String,
    title: String,
    content: String
): Notification {
    val bigTextStyle = NotificationCompat.BigTextStyle()
    bigTextStyle.bigText(content)

    val intent = Intent(context, MainActivity::class.java)
//    val deepLinkIntent = Intent(
//        Intent.ACTION_VIEW,
//        "https://www.example.com/$id".toUri(),
//        context,
//        MainActivity::class.java
//    )

    val id = "exampleId"
    val deepLinkIntent = Intent(
        Intent.ACTION_VIEW,
        NotificationScreen.Detail.route.toUri(),
        context,
        MainActivity::class.java
    )

    val deepLinkPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
        addNextIntentWithParentStack(deepLinkIntent)
        getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    val pendingIntent =
        PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)


    val receiverIntent = Intent(context, MyReceiver::class.java)
    receiverIntent.putExtra("MESSAGE", "Success")
    val receiverPendingIntent =
        PendingIntent.getBroadcast(context, 0, receiverIntent, PendingIntent.FLAG_IMMUTABLE)

    val clickIntent = Intent(
        Intent.ACTION_VIEW,
        "$MY_URI/$MY_ARG=Coming From Notification".toUri(),
        context,
        MainActivity::class.java
    )
    val clickPendingIntent = TaskStackBuilder.create(context).run {
        addNextIntentWithParentStack(clickIntent)
        getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    return NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setAutoCancel(true)
        .addAction(R.drawable.ic_launcher_background, "Show Toast", receiverPendingIntent)
        .setContentIntent(clickPendingIntent)
        .setContentTitle(title)
        .setContentText(content)
        .setStyle(bigTextStyle)
        .build()
}

private fun getUniqueId() = ((System.currentTimeMillis() % 10000).toInt())


