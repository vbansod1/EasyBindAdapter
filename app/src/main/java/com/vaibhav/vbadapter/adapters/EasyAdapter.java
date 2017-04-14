package com.vaibhav.vbadapter.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vaibhav.vbadapter.EasyField;
import com.vaibhav.vbadapter.Utils.AttributeParser;
import com.vaibhav.vbadapter.Utils.EasyView;
import com.vaibhav.vbadapter.interfaces.IViewType;
import com.vaibhav.vbadapter.viewholder.UltimateViewHolder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vaibhav Bansod on 4/4/2017.
 */

public class EasyAdapter<T> extends EasyRecyclerAdapter<T> {
    private static final String TAG = "EasyAdapter";
    private static AttributeParser mAttributeParser;
    private LayoutInflater mLayoutInflater;

    private EasyAdapter() {
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static EasyAdapter with(Context context) {
        if (context == null) {
            return null;
        }
        EasyAdapter easyAdapter = new EasyAdapter();
        easyAdapter.context = context;
        mAttributeParser = new AttributeParser();
        easyAdapter.mLayoutInflater = mAttributeParser.getLayoutInflater(LayoutInflater.from(context));
        return easyAdapter;
    }

    public EasyAdapter resource(int resourceId) {
        this.mLayoutResId = resourceId;
        return this;
    }

    public EasyAdapter items(List<T> list, Class modelClass) {
        mData = list;
        this.modelClass = modelClass;
        return this;
    }

    public void into(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        recyclerView.setAdapter(this);
    }


    @Override
    public UltimateViewHolder onCreate(@Nullable View convertView, ViewGroup parent, int viewType) {
        if (convertView == null) {
            View itemView = mLayoutInflater.inflate(mMulItemViewType == null ?
                    mLayoutResId : mMulItemViewType.getLayoutId(viewType), parent, false);
            mAttributeParser.setViewAttribute(itemView);
            Map<Integer, HashMap<Integer, String>> attributeList = mAttributeParser.getAttributeList();
            Log.e(TAG, "onCreate: " + attributeList);
            return UltimateViewHolder.get(null, itemView);
        } else { // When convertView != null, parent must be an AbsListView.
            return UltimateViewHolder.get(convertView, null);
        }
    }

    @Override
    public void onBind(UltimateViewHolder holder, int viewType, int layoutPosition, Object item) {
        for (Field field : item.getClass().getDeclaredFields()) {
            try {
                Object o = field.get(item);
                EasyField annotation = field.getAnnotation(EasyField.class);
                if (annotation != null) {
                    EasyView easyView = new EasyView(annotation.tag());
                    if (holder.easyViewList.contains(easyView)) {
                        EasyView easyView1 = holder.easyViewList.get(holder.easyViewList.indexOf(easyView));
                        if (easyView1.view instanceof TextView) {
                            ((TextView) easyView1.view).setText(((String) o));
                        } else if (easyView1.view instanceof ImageView) {
                            Glide.with(context)
                                    .load(((String) o))
                                    .dontAnimate()
                                    .into(((ImageView) easyView1.view));
                        } else if (easyView1.view instanceof Button) {
                            ((Button) easyView1.view).setText(((String) o));
                        }
                    }

                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}




