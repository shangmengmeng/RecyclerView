package com.example.administrator.myapplication10;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by FancyMenG on 2017/9/10.
 */

public class StringRecyclerAdapter extends RecyclerView.Adapter<StringRecyclerAdapter.ViewHolder> {
    private ArrayList<String> data;//用于显示姓名


    private Context context;

    public StringRecyclerAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;


    }

    public StringRecyclerAdapter(Context context) {
        this.context = context;

    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public ArrayList<String> getData() {
        return data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_1, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.setData(data,position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        ImageView iv_delete;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_item);
        }
        public void setData(ArrayList<String> data, int position) {
            tv_name.setText(data.get(position));
        }
    }
}
