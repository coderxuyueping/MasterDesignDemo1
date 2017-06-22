package com.example.tiange.tablayoutandrecycler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.tiange.updatemain.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xyp
 * Date: 2017/3/9
 * Time: 17:36
 */

public class Fragment1 extends Fragment {
    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private List<String> data;
    private RecyclerAdapter mAdapter;

    private Handler mHandler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                swipeRefreshLayout.setRefreshing(false);
                mAdapter.notifyDataSetChanged();//调用onBindViewHolder，和listview的adapter调用getview一样的
            }
        }
    };
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data=new ArrayList<String>();
        for(int i=0;i<3;i++){
            data.add("原始数据"+i);
        }
        mAdapter=new RecyclerAdapter(data,getActivity());

    }

    //用户可见的时候，懒加载
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            getData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_lay,container,false);
    }

    //当view创建好了执行
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view=view;
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swiprefresh);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerview);

        //设置刷新时箭头的颜色
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.color_primary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //正在刷新
                swipeRefreshLayout.setRefreshing(true);
                getData();
            }
        });

        //recyclerview更加重视viewholder的复用
        //设置recyclerview的管理器，水平管理器类似listview
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);

        //监听recyclerview的滚动，当可见的子item不是第一个的时候，swiprefresh的刷新要禁用
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isCanRefresh=false;
                if(recyclerView!=null&&recyclerView.getChildCount()>0){
                    LinearLayoutManager manager= (LinearLayoutManager) recyclerView.getLayoutManager();
//                    manager.findLastVisibleItemPosition();//当前视图最后可见的item的position
//                    recyclerView.scrollToPosition(10);//跳到指定位置
//                    recyclerView.smoothScrollToPosition(0);//平滑的滚动到某个位置

                    if(manager.findFirstVisibleItemPosition()==0){//当前视图第一个可见的item positino
                        isCanRefresh=true;
                    }
                }
                swipeRefreshLayout.setEnabled(isCanRefresh);
            }
        });
    }

    //模拟网络加载数据
    private void getData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    for(int i=0;i<3;i++){
                        data.add("数据"+i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(1);
            }
        }).start();
    }


}
