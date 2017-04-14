package com.vaibhav.vbadapter.interfaces;

import android.support.annotation.LayoutRes;

/**
 * Created by Vaibhav Bansod on 4/5/2017.
 */

public interface IViewType<T> {

    /**
     * @return Will not be called if using a RecyclerView.
     */
    int getViewTypeCount();

    /**
     * Item view type, a non-negative integer is better.
     *
     * @param position current position of ViewHolder
     * @param t        model item
     * @return viewType
     */
    int getItemViewType(int position, T t);

    /**
     * Layout res.
     *
     * @param viewType {@link #getItemViewType(int, T)}
     * @return {@link android.support.annotation.LayoutRes}
     */
    @LayoutRes
    int getLayoutId(int viewType);
}

