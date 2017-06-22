package com.example.tiange.tablayoutandrecycler;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.tiange.updatemain.R;

/**
 * User: xyp
 * Date: 2017/3/10
 * Time: 11:13
 */

public class MyDialogFragment extends DialogFragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.dialogfrg_lay,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view=view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
     /*   AlertDialog dialog=new AlertDialog.Builder(getActivity())
                .setTitle("DialogFragment对话框")
                .setMessage("DialogFragment对话框的message")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消",null).show();
        return dialog;*/
//        return new Dialog(getActivity(),R.style.dialog_transparent);//设置透明背景
        return new Dialog(getActivity(),R.style.dialog_transparent){
            @Override
            public void onBackPressed() {
                super.onBackPressed();//也可以监听dialog的返回键
            }
        };//设置透明背景
    }
}
