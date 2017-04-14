package com.vaibhav.vbadapter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.vaibhav.vbadapter.interfaces.IBindData;
import com.vaibhav.vbadapter.interfaces.IHeaderFooter;
import com.vaibhav.vbadapter.interfaces.ILayoutManager;
import com.vaibhav.vbadapter.interfaces.IViewType;
import com.vaibhav.vbadapter.viewholder.UltimateViewHolder;

import java.util.List;

/**
 * Created by Vaibhav Bansod on 4/5/2017.
 */

abstract class EasyRecyclerAdapter<T> extends RecyclerView.Adapter<UltimateViewHolder>
        implements IBindData<T, UltimateViewHolder>, IViewType<UltimateViewHolder>, ILayoutManager {

    protected RecyclerView mRecyclerView;
    List mData;
    protected int mLayoutResId;
    protected IViewType mMulItemViewType;
    public Class modelClass;
    protected Context context;

    @Override
    public UltimateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreate(null, parent, viewType);
    }

    @Override
    public void onBindViewHolder(UltimateViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        onBind(holder, viewType, position, mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }


    @Override
    public int getViewTypeCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position, UltimateViewHolder ultimateViewHolder) {
        if (mMulItemViewType != null) {
            return mMulItemViewType.getItemViewType(position, mData.get(position));
        }
        return 0;
    }


    @Override
    public boolean hasLayoutManager() {
        return mRecyclerView != null && mRecyclerView.getLayoutManager() != null;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return hasLayoutManager() ? mRecyclerView.getLayoutManager() : null;
    }

    /**
     * This is for header and footer
     * @param viewType {@link #getItemViewType(int, T)}
     * @return
     */
    @Override
    public int getLayoutId(int viewType) {
        return 0;
    }
}
