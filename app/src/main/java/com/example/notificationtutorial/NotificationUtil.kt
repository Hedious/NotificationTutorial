package com.example.notificationtutorial

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.StringRes
import androidx.core.app.NotificationCompat

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
    val pendingIntent =
        PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    return NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setAutoCancel(true)
        .addAction(R.drawable.ic_launcher_background, "Open Noti", pendingIntent)
        .setContentTitle(title)
        .setContentText(content)
        .setContentIntent(pendingIntent)
        .setStyle(bigTextStyle)
        .build()
}

private fun getUniqueId() = ((System.currentTimeMillis() % 10000).toInt())


