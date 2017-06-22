package com.example.tiange.tablayoutandrecycler;

import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tiange.updatemain.R;

/**
 * User: xyp
 * Date: 2017/3/9
 * Time: 17:36
 */

public class Fragment2 extends Fragment {
    private  View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frg_2lay,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view=view;
        view.findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action1();
            }
        });
        view.findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action2();
            }
        });
        view.findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action3();
            }
        });
        view.findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action4();
            }
        });
        view.findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action5();
            }
        });
        view.findViewById(R.id.btn6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action6();
            }
        });
        view.findViewById(R.id.btn7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action7();
            }
        });
    }

    //第一种方式创建出来的dialog通过create
    private void action1(){
        AlertDialog dialog=new AlertDialog.Builder(getActivity())
                           .setTitle("第一个对话框")
                           .setMessage("第一个对话框的message")
                           .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   dialog.dismiss();
                               }
                           })
                           .setNegativeButton("取消",null).create();
        dialog.show();
    }

    //第二种方式创建出来的对话框，通过内部类里的show，这个show会自己调用create创建出一个dialog然后show。
    private void action2(){
        AlertDialog dialog=new AlertDialog.Builder(getActivity())
                .setTitle("第二个对话框")
                .setMessage("第2个对话框的message")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消",null).show();
    }

    //自定义布局的dialog
    private void action3(){
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.dialog_view,null,false);
        ViewGroup parent= (ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeAllViews();
        }

        Button btn= (Button) view.findViewById(R.id.dialog_btn);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        final AlertDialog dialog=builder.create();
        dialog.show();
        dialog.setContentView(view);//必须在show之后设置
        Window window=dialog.getWindow();

        //设置大小和位置
        WindowManager.LayoutParams lp= window.getAttributes();
        lp.width=WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height=WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity= Gravity.CENTER;
        dialog.onWindowAttributesChanged(lp);

        //设置动画
        window.setWindowAnimations(R.style.dialogAani);

        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void action4(){
        CheckBox checkBox=new CheckBox(getActivity());
        checkBox.setTextSize(12);
        checkBox.setText("不再显示");
        checkBox.setTextColor(getResources().getColor(R.color.colorAccent));
        RelativeLayout rl=new RelativeLayout(getActivity());
        RelativeLayout.LayoutParams l=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        rl.addView(checkBox,l);

        AlertDialog dialog=new AlertDialog.Builder(getActivity()).setTitle("对话框").setMessage("mess")
                .setView(rl).setPositiveButton("确定",null).setNegativeButton("取消",null).show();
    }

    private void action5(){
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.progress_dialog_lay,null,false);
        ViewGroup parent= (ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeAllViews();
        }

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        final AlertDialog dialog=builder.create();
        dialog.show();
        dialog.setContentView(view);//必须在show之后设置
        Window window=dialog.getWindow();

        //设置大小和位置
        WindowManager.LayoutParams lp= window.getAttributes();
        lp.width=WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height=WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity= Gravity.CENTER;
        dialog.onWindowAttributesChanged(lp);
    }

    //通过dialogfragment创建对话框，google在design规范提倡这样创建对话框
    private void action6(){
        MyDialogFragment fragment=new MyDialogFragment();
        fragment.show(getActivity().getSupportFragmentManager(),"dialog");

        //dismissAllowingStateLoss();dialogFragment里的这个方法，会执行dismissDialog，并且如果这个frgemnt
        //在后退栈中，就会把他移除并，如果不在，就把他从FragmentManager中移除
        //FragmentManager中的commit和commitAllowingStateLoss都是把事务提交，但是如果activity的状态没保存（有些参数系统还没有保存）使用commit会报错
    }

    private void action7(){
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.pop_layout,null,false);
        ViewGroup parent= (ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeAllViews();
        }

        PopupWindow popupWindow=new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.dialogAani);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);//设置pop窗体是否可以点击
        popupWindow.showAtLocation(this.view,Gravity.BOTTOM,0,0);
    }
}
