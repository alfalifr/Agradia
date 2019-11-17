package id.go.surabaya.disperdagin.utilities

import android.app.Notification
import android.media.RingtoneManager
import android.content.ContentResolver
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import android.text.Html
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import id.go.surabaya.disperdagin.R
import id.go.surabaya.disperdagin.activities.MainActivity
import id.go.surabaya.disperdagin.activities.OrderDetailAct
import id.go.surabaya.disperdagin.activities.TransactionDetailAct
import id.go.surabaya.disperdagin.models.Notif
import id.go.surabaya.disperdagin.models.Transaction

class NotificationUtils(private val mContext: Context) {
    private var activityMap: MutableMap<String, Class<*>> = HashMap()
    private val activityDest = arrayOf(OrderDetailAct::class.java, TransactionDetailAct::class.java, TransactionDetailAct::class.java)

    init {
        //Populate activity map
        activityMap["MainActivity8"] = MainActivity::class.java
     //   activityMap["SecondActivity"] = SecondActivity::class.java!!
    }

    fun displayNotification(notif: Notif, resultIntent: Intent) {
        var resultIntent = resultIntent
        run {
            val message = notif.body
            val title = notif.title
            val type = notif.type
            val orderId = notif.id_order
            val destination = notif.actionDest
            var iconBitMap: Bitmap? = null


            Log.d("hasil notif data", message + title + "tipe "+ type + "id_order" + orderId)

            val icon = R.mipmap.ic_launcher

            val resultPendingIntent: PendingIntent

            if (type != null) {
               when(type.toInt()){
                   Constants.STATUS_TRANSACTION_UNCONFIRMED -> resultIntent = Intent(mContext, activityDest[0])
                   Constants.STATUS_TRANSACTION_REJECTED -> resultIntent = Intent(mContext, activityDest[1])
                   Constants.STATUS_TRANSACTION_APPROVED -> resultIntent = Intent(mContext, activityDest[2])
               }

                val transaction = Transaction(orderId!!,null,null,null, null, null, null)

                resultIntent.putExtra(Constants.EXTRA_TRANSACTION_ITEM, transaction)

                resultPendingIntent = PendingIntent.getActivity(
                    mContext,
                    0,
                    resultIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT
                )
            } else {
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                resultPendingIntent = PendingIntent.getActivity(
                    mContext,
                    0,
                    resultIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT)
            }


           /* if (URL == action) {
                val notificationIntent = Intent(Intent.ACTION_VIEW, Uri.parse(destination))

                resultPendingIntent = PendingIntent.getActivity(mContext, 0, notificationIntent, 0)
            } else if (ACTIVITY == action && activityMap.containsKey(destination)) {
                resultIntent = Intent(mContext, activityMap.get(destination))

                resultPendingIntent = PendingIntent.getActivity(
                    mContext,
                    0,
                    resultIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT
                )
            } else {
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                resultPendingIntent = PendingIntent.getActivity(
                    mContext,
                    0,
                    resultIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT)
            }*/

            val mBuilder = NotificationCompat.Builder(
                mContext, CHANNEL_ID
            )

            val notification: Notification

            if (iconBitMap == null) {
                //When Inbox Style is applied, user can expand the notification
                val inboxStyle = NotificationCompat.InboxStyle()

                inboxStyle.addLine(message)
                notification = mBuilder.setSmallIcon(icon).setTicker(title).setWhen(0)
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setContentIntent(resultPendingIntent)
                    .setStyle(inboxStyle)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(mContext.resources, icon))
                    .setContentText(message)
                    .build()

            } else {
                //If Bitmap is created from URL, show big icon
                val bigPictureStyle = NotificationCompat.BigPictureStyle()
                bigPictureStyle.setBigContentTitle(title)
                bigPictureStyle.setSummaryText(Html.fromHtml(message).toString())
                bigPictureStyle.bigPicture(iconBitMap)
                notification = mBuilder.setSmallIcon(icon).setTicker(title).setWhen(0)
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setContentIntent(resultPendingIntent)
                    .setStyle(bigPictureStyle)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icon))
                    .setContentText(message)
                    .build()
            }

            val notificationManager =
                mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            //All notifications should go through NotificationChannel on Android 26 & above
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(channel)

            }
            notificationManager.notify(NOTIFICATION_ID, notification)
        }
    }

    /**
     * Downloads push notification image before displaying it in
     * the notification tray
     *
     * @param strURL : URL of the notification Image
     * @return : BitMap representation of notification Image
     */
/*    private fun getBitmapFromURL(strURL: String): Bitmap? {
        try {
            val url = URL(strURL)
            val connection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input = connection.getInputStream()
            return BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

    }*/

    /**
     * Playing notification sound
     */
    fun playNotificationSound() {
        try {
            val alarmSound = Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE
                        + "://" + mContext.getPackageName() + "/raw/notification"
            )
            val r = RingtoneManager.getRingtone(mContext, alarmSound)
            r.play()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    companion object {
        private val NOTIFICATION_ID = 200
        private val PUSH_NOTIFICATION = "pushNotification"
        private val CHANNEL_ID = "myChannel"
        private val CHANNEL_NAME = "myChannelName"
        private val URL = "url"
        private val ACTIVITY = "activity"
    }
}