package com.vaibhav.vbadapter.interfaces;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Vaibhav Bansod on 4/5/2017.
 */
public interface IBindData<T, VH> {


    VH onCreate(@Nullable View convertView, ViewGroup parent, int viewType);

    void onBind(VH holder, int viewType, int layoutPosition, Object item);
}
