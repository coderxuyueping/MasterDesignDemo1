package com.example.tiange.updatemain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

/**
 * User: xyp
 * Date: 2017/3/14
 * Time: 18:23
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main3Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //这两个方法都可以监听回退键，但是onkeydow兼容Android 1.0到Android 2.1 也是常规方法
    //Android 2.0开始又多出了一种新的方法onBackPressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){
            //监听到back按键，并且不是重复按
        }
        return super.onKeyDown(keyCode, event);
    }
}
