package me.leolin;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import me.leolin.stock.R;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author leolin
 */
@Aspect
public class DebugNotificationAdvice {

    @After("execution(* me.leolin.stock.ui.MainActivity.onCreate(..))")
    public void afterMainActivityOnCreate(JoinPoint joinPoint) {
        Activity activity = (Activity) joinPoint.getTarget();
        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(9999,
                new Notification.Builder(activity)
                        .setTicker("Test")
                        .setContentTitle("Title")
                        .setContentText("Content")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .getNotification()
        );
    }
}
