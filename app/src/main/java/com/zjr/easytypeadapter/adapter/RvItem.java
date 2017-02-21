package com.zjr.easytypeadapter.adapter;

import android.support.annotation.LayoutRes;

import java.util.Collection;

/**
 * Created by zjr on 2017/1/24.
 */

public class RvItem<T>{
    public int index;
    public T data;
    public int viewTypeId;

    public RvItem(T data,@LayoutRes int viewTypeId) {
        this.data = data;
        this.viewTypeId = viewTypeId;
    }

    public RvItem(@LayoutRes int viewTypeId) {
        this.viewTypeId = viewTypeId;
    }

    public int getCount(){
        if(data!=null&&data instanceof Collection){
            int size = ((Collection) data).size();
            return size;
        }
        return 1;
    }
}
