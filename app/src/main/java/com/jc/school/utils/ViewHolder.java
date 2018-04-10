package com.jc.school.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jc.school.R;
import com.squareup.picasso.Picasso;

/**
 * 通用Viewholer
 * Created by Jaycee on 15/12/24.
 * E-mail：jayceeok@foxmail.com
 */
public class ViewHolder {

    private SparseArray<View> mViews;

    public int getPosition() {
        return mPosition;
    }

    private int mPosition;
    private View mConvertView;


    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }


    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId,
                                 int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }


    /**
     * 通过viewId获取控件
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }


    public View getmConvertView() {
        return mConvertView;
    }


    /**
     * 设置TextView的值
     */
    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }


    /**
     * 设置图片来源
     */
    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }


    /**
     * 设置bitmap来源
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }


    /**
     * 设置网络图片来源
     */
    public ViewHolder setImageURL(Context context, int viewId, String url) {
        ImageView view = getView(viewId);
        Picasso.with(context).load(url).placeholder(R.drawable.image_default)//没有加载图片时显示的默认图像
                .error(R.drawable.image_default)
                        // 图像加载错误时显示的图像
                .resize(140, 140).into(view);
        return this;
    }



}

