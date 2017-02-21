package com.zjr.easytypeadapter.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zjr on 2017/1/24.
 */
public class BaseItemHolder extends RecyclerView.ViewHolder{

    private SparseArray<View> mViews;

    public BaseItemHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    public BaseItemHolder(ViewGroup parent, @LayoutRes int res) {
        this(LayoutInflater.from(parent.getContext()).inflate(res, parent, false));
    }

    public BaseItemHolder(Context context, @LayoutRes int res) {
        this(LayoutInflater.from(context).inflate(res, null, false));
    }

    public Context getContext(){
        return itemView.getContext();
    }

    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public void setText(int viewId, String text)
    {
        TextView tv = getView(viewId);
        tv.setText(text);
    }

    public void setTextColor(int viewId, int textColor)
    {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
    }

    public void setImageResource(int viewId, int resId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
    }


    public void setBackgroundColor(int viewId, int color)
    {
        View view = getView(viewId);
        view.setBackgroundColor(color);
    }

    public void setBackgroundRes(int viewId, int backgroundRes)
    {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
    }

    public void setTag(int viewId, Object tag)
    {
        View view = getView(viewId);
        view.setTag(tag);
    }

    public void setVisible(int viewId, boolean visible)
    {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setChecked(int viewId, boolean checked)
    {
        Checkable view = getView(viewId);
        view.setChecked(checked);
    }

    public void setOnClickListener(int viewId, View.OnClickListener listener)
    {
        View view = getView(viewId);
        view.setOnClickListener(listener);
    }

    public void setOnItemClickListener(View.OnClickListener listener)
    {
        itemView.setOnClickListener(listener);
    }

}
