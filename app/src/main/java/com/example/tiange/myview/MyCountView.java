package com.example.tiange.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.tiange.updatemain.R;

/**
 * User: xyp
 * Date: 2017/3/14
 * Time: 17:12
 */

public class MyCountView extends View{
    private Paint mPaint;
    private int count;
    private int color;
    private int textSize;
    private CountDownTimer countDownTimer;
    private EndListener listener;
    private int tempCount;
    public MyCountView(Context context) {
        this(context,null);
    }

    public MyCountView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyCountView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs);
        init();
    }
    private void initAttrs(Context context,AttributeSet attrs){
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.MyCountView);
        count=typedArray.getInteger(R.styleable.MyCountView_count,0);
        color=getResources().getColor(typedArray.getResourceId(R.styleable.MyCountView_textColor,0));
        textSize=typedArray.getInt(R.styleable.MyCountView_textSize,12);
        tempCount=count;
        typedArray.recycle();
    }
    private void init(){
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);
        mPaint.setFakeBoldText(true);//粗体
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(textSize);
        mPaint.setTextAlign(Paint.Align.CENTER);


         countDownTimer=new CountDownTimer((count)*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tempCount--;
                invalidate();
            }

            @Override
            public void onFinish() {
                Log.e("tag","done");
                if (listener != null) {
                    listener.end();
                }
            }
        };
    }

    int width=0;
    int height=0;
    //测量自己的大小并设置，如果是viewgroup就测量所有ziview的大小，并设置viewgroup自己的大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w=0,h=0;
         width=MeasureSpec.getSize(widthMeasureSpec);
         height=MeasureSpec.getSize(heightMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        //如果是Exactly就直接给父容器给我们提供的测量值，如果是At_Most(wrap_content)就计算值
        if(widthMode==MeasureSpec.AT_MOST){
            //at_most有多大就给多少，类似于wrap_content
            w=width;//最大就是测量出来的值
        }else if(widthMode==MeasureSpec.EXACTLY){
            //精确值，就是直接在布局里给大小的，或者是Match_parent
            w=width;
        }else if(widthMode==MeasureSpec.UNSPECIFIED){
            //未给定大小，子布局想多大就多大，一般很少使用
            w=100;
        }

        if(heightMode==MeasureSpec.AT_MOST){
            //at_most有多大就给多少，类似于wrap_content
            h=height;//最大就是测量出来的值
        }else if(heightMode==MeasureSpec.EXACTLY){
            //精确值，就是直接在布局里给大小的，或者是Match_parent
            h=height;
        }else if(heightMode==MeasureSpec.UNSPECIFIED){
            //未给定大小，子布局想多大就多大，一般很少使用
            h=100;
        }

//        //如果要让这个view是正方形
//        w=Math.max(w,h);
//        h=w;
        setMeasuredDimension(w,h);//设置自己的大小
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(String.valueOf(tempCount),width/2,height/2,mPaint);
    }

    public void start(){
        countDownTimer.start();
    }
    public void stop(){
        countDownTimer.cancel();
        tempCount=count;
    }
    public void setEndListener(EndListener listener){
        this.listener=listener;
    }
}
