package com.example.tiange.tablayoutandrecycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tiange.myview.EndListener;
import com.example.tiange.myview.MyCountView;
import com.example.tiange.updatemain.R;

/**
 * User: xyp
 * Date: 2017/3/9
 * Time: 17:36
 */

public class Fragment3 extends Fragment {
    private MyCountView myCountView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frg_3lay,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myCountView= (MyCountView) view.findViewById(R.id.myview);
        myCountView.setEndListener(new EndListener() {
            @Override
            public void end() {
                myCountView.stop();
            }
        });
        view.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCountView.start();
            }
        });
    }
}
