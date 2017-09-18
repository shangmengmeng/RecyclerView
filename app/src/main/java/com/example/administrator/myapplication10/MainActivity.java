package com.example.administrator.myapplication10;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_1);

        final ArrayList<String> a = new ArrayList<>();
        a.add("ahha1");
        a.add("ahha2");
        a.add("ahha3");
        a.add("ahha4");
        a.add("ahha5");
        a.add("ahha6");


        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        //这里是分割线的用法
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);//Adapter内Item的改变不会影响RecyclerView宽高的时候，可以设置为true让RecyclerView避免重新计算大小


        CommonAdapter   adapter1= new CommonAdapter<String>(this, R.layout.item_1, a) {
            @Override
            protected void convert(final ViewHolder holder, final String o, final int position) {
                holder.setText(R.id.tv_item, o);
                //点击事件
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "我是点击事件" + position, Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, o, Toast.LENGTH_SHORT).show();

                    }
                });
                holder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Toast.makeText(MainActivity.this, "我是长按事件" + position, Toast.LENGTH_SHORT).show();
                        return true;//这个拦截点击时间
                    }
                });
            }

        };
        MyItemTouchHelperCallBack callBack = new MyItemTouchHelperCallBack(adapter1);
        ItemTouchHelper helper = new ItemTouchHelper(callBack);
        helper.attachToRecyclerView(recyclerView);
        //鴻洋大神的baseAdapter
        recyclerView.setAdapter(adapter1);


    }
}
