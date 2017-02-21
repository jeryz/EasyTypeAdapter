package com.zjr.easytypeadapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

/**
 * Created by zjr on 2017/1/24.
 */

public abstract class EasyTypeAdapter extends RecyclerView.Adapter {

    private SparseArray<RvItem> dataArray = new SparseArray<>();


    public final <T> void addItem(RvItem<T> item) {
        item.index = dataArray.size();
        dataArray.put(item.index, item);
    }

    public final <T> void addItem(RvItem<T> item, int index) {
        if (index > 0) {
            item.index = index;
            //SparseArray会根据key的大小排序
            dataArray.put(item.index, item);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewTypeId) {
        return new BaseItemHolder(parent, viewTypeId);
    }

    @Override
    public int getItemViewType(int position) {
        int count = 0;
        for (int i = 0; i < dataArray.size(); i++) {
            RvItem rvItem = dataArray.valueAt(i);
            int size = rvItem.getCount();
            if (position >= count && position < count + size) {
                return rvItem.viewTypeId;
            }
            count += size;
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseItemHolder baseHolder = (BaseItemHolder) holder;
        if (dataArray.size() == 1) {
            RvItem rvItem = dataArray.valueAt(0);
            onBindView(baseHolder, rvItem, position, position);
        } else {
            int rvItemIndex = 0;
            int start = 0;
            for (int i = 0; i < dataArray.size(); i++) {
                RvItem rvItem = dataArray.valueAt(i);
                if (holder.getItemViewType() == rvItem.viewTypeId) {
                    rvItemIndex = i;
                    break;
                }
                start += rvItem.getCount();
            }
            int dataPosition = position - start;
            RvItem rvItem = dataArray.valueAt(rvItemIndex);
            onBindView(baseHolder, rvItem, dataPosition, position);
        }
    }

   public abstract void onBindView(BaseItemHolder baseHolder, RvItem rvItem, int position, int layoutPosition);

    @Override
    public int getItemCount() {
        int count = 0;
        for (int i = 0; i < dataArray.size(); i++) {
            RvItem rvItem = dataArray.valueAt(i);
            count += rvItem.getCount();
        }
        return count;
    }

    public final int getStartPositionOfType(int type){
        int start = 0;
        for (int i = 0; i < dataArray.size(); i++) {
            RvItem rvItem = dataArray.valueAt(i);
            if (type == rvItem.viewTypeId) {
                return start;
            }
            start += rvItem.getCount();
        }
        return start;
    }

    public void notifyDataChanged(int viewType, int itemCount){
        notifyItemRangeChanged(getStartPositionOfType(viewType),itemCount);
    }
}
