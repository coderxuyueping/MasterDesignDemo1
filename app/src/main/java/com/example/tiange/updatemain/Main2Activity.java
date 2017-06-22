package com.example.tiange.updatemain;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
//BottomSheetDialog
/**
 * User: xyp
 * Date: 2017/3/14
 * Time: 18:23
 */
public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    //第一次启动实例不会回掉，以后每次启动该实例都会在onCreate后执行
    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){
            try{
                //这段代码是回到Home桌面，类似于按了Home键回到桌面而不是按back键
                Intent intent=new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_HOME);
                this.startActivity(intent);
            }catch (Exception e){

            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //如果当前进程不是在前台运行
        if (!Util.isAppOnForeground(this,getPackageName())) {
            sendNotification();
        }
    }

    //创建一个通知，可以跳回应用
    private void sendNotification() {
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        Intent resultIntent = new Intent(this,Main2Activity.class);
        resultIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resultIntent.putExtra("notification_key", "notification");
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notificationCompat = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_round)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("跳转")
                .setWhen(System.currentTimeMillis())
                .setOngoing(true)
                .setContentIntent(resultPendingIntent)
                .build();
        notificationCompat.flags |= Notification.FLAG_NO_CLEAR;
        notificationManager.notify(0,notificationCompat);
    }

    private LinearLayout content;
    protected ActionBar actionBar;


    @Override
    public void onResume() {
        super.onResume();
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(0);
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
    }

}
