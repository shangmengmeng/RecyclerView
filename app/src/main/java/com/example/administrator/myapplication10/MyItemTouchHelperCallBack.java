package com.example.administrator.myapplication10;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by FancyMenG on 2017/9/17.
 * ItemTouchHelper 一个帮助开发人员处理拖拽和滑动删除的实现类(辅助类）
 */


public class MyItemTouchHelperCallBack extends ItemTouchHelper.Callback {

    private CommonAdapter adapter;

    public MyItemTouchHelperCallBack(CommonAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            return makeMovementFlags(dragFlags, swipeFlags);
        }

    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        // 不同Type之间不可移动
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }

        int fromPosition = viewHolder.getAdapterPosition();//获取原来的位置
        int toPosition = target.getAdapterPosition();//获取目标位置
        //数据交换位置
        //  list-- 在该列表中的调剂元素。
        //  i-- 要交换的一个元素的索引。
        // j-- 要交换的其它元素的索引。

        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(adapter.getDatas(), i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(adapter.getDatas(), i, i - 1);
            }
        }
        adapter.notifyItemMoved(fromPosition,toPosition);
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    /**
     *
     * 设置adapter的点击反馈onSelectedChanged+clearView
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        //不在闲置状态
        if (actionState!=ItemTouchHelper.ACTION_STATE_IDLE){
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#f2ebeb"));
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }
}
