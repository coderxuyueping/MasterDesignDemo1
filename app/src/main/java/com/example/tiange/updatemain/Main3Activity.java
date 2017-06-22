package com.example.tiange.updatemain;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.tiange.tablayoutandrecycler.Main4Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xyp
 * Date: 2017/3/14
 * Time: 18:23
 */
public class Main3Activity extends AppCompatActivity {
    private AppCompatAutoCompleteTextView city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        city= (AppCompatAutoCompleteTextView) findViewById(R.id.city);
        fillUserAutoList();
        ((TextInputEditText) findViewById(R.id.acount)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int count=s.toString().length();
                Toast.makeText(Main3Activity.this,count+"",Toast.LENGTH_SHORT).show();
                if(count<5){
                    ((TextInputLayout)findViewById(R.id.acount_layout)).setError("格式错误");
                }else{
                    ((TextInputLayout)findViewById(R.id.acount_layout)).setError(null);
                    ((TextInputLayout)findViewById(R.id.acount_layout)).setErrorEnabled(false);
//                    ((TextInputLayout)findViewById(R.id.acount_layout)).setEnabled(false);//设置不可用
                }
            }
        });
    }

    private List<String> mCitys=new ArrayList<>();

    private void fillUserAutoList() {
        for(int i=0;i<4;i++){
            mCitys.add("成都"+i);
        }
        //配置适配器
        String[] idString = new String[mCitys.size()];
        for (int i = 0; i < mCitys.size(); i++) {
            idString[i] = mCitys.get(i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.city_item, idString);
        city.setAdapter(adapter);
        city.setThreshold(0);//输入的次数是第几个的时候开始匹配
        city.setSingleLine();
        city.dismissDropDown();
        city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String citys = parent.getAdapter().getItem(position).toString();
                city.setText(citys);
            }
        });
    }

    public void toNext(View v){
        Intent intent=new Intent(this, MyTabLayoutTitleAc.class);
        startActivity(intent);
    }
}
