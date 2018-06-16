package com.jc.school.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jc.school.R;
import com.jc.school.bean.NewsInfo;

import java.util.List;

/**
 * @author jiangchao
 * @Module.Name
 * @Create.Date 2018/6/17 上午12:53
 * @Update.By jiangchao
 * @Update.Content
 * @Update.Date 2018/6/17 上午12:53
 * @see
 */


public class NewsAdapter extends BaseQuickAdapter<NewsInfo.ResultEntity.ListEntity, BaseViewHolder> {

    List<NewsInfo.ResultEntity.ListEntity> mDatas;

    public NewsAdapter(int layoutResId, @Nullable List<NewsInfo.ResultEntity.ListEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsInfo.ResultEntity.ListEntity item) {
        helper.setText(R.id.tv_time, item.getTime()).setText(R.id.tv_title, item.getTitle());
    }



    /**
     * 下拉刷新，清除原有数据，添加新数据
     *
     * @param newData
     */
    public void refreshData(List<NewsInfo.ResultEntity.ListEntity> newData) {
        mDatas.clear();
        mDatas.addAll(newData);
        notifyItemRangeChanged(0, mDatas.size());
    }

    /**
     * 在原来数据的末尾追加新数据
     *
     * @param moreData
     */
    public void loadMoreData(List<NewsInfo.ResultEntity.ListEntity> moreData) {
        int lastPosition = mDatas.size();
        mDatas.addAll(lastPosition, moreData);
        notifyItemRangeInserted(lastPosition, moreData.size());
    }
}
