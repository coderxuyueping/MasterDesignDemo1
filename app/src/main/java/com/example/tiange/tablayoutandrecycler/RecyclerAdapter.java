package com.example.tiange.tablayoutandrecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tiange.updatemain.R;

import java.util.List;

/**
 * User: xyp
 * Date: 2017/3/9
 * Time: 17:50
 */

public class RecyclerAdapter extends RecyclerView.Adapter{
    private List<String> data;
    private Context context;
    public RecyclerAdapter(List<String> data,Context context){
        this.data=data;
        this.context=context;
    }

    private static final class Type{
        static final int TYPE1=0;
        static final int TYPE2=1;
    }

    //创建viewholder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View itemView;
        if(viewType==Type.TYPE1){
            itemView= LayoutInflater.from(context).inflate(R.layout.item_rv1,parent,false);
            viewHolder=new ViewHolder1(itemView);
        }else if(viewType==Type.TYPE2){
            itemView=LayoutInflater.from(context).inflate(R.layout.item_rv2,parent,false);
            viewHolder=new ViewHolder1(itemView);
        }else{
            viewHolder=null;
        }
        return viewHolder;
    }

    //数据绑定
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==Type.TYPE1){
            ViewHolder1 viewHolder1= (ViewHolder1) holder;
            viewHolder1.tv.setText(data.get(position));
        }else if(getItemViewType(position)==Type.TYPE2){
            //因为此时的holder是viewholder1了，如果viewholder1不是2的子类，则会爆类型转换异常
            ViewHolder2 viewHolder2= (ViewHolder2) holder;
            viewHolder2.tv.setText(data.get(position));
        }

    }
    //注意这里要是ViewHOlder2
    class ViewHolder1 extends ViewHolder2{
        TextView tv;

        public ViewHolder1(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.item_tv1);
        }
    }
    class ViewHolder2 extends RecyclerView.ViewHolder{
        TextView tv;
        public ViewHolder2(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.item_tv2);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return Type.TYPE1;
        }else{
            return Type.TYPE2;
        }
    }
}
