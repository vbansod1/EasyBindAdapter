package com.vaibhav.vbadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vaibhav Bansod on 4/4/2017.
 */

public class VbAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private Context context;
    private Class<T> modelClass;
    private Class<VH> viewHolderClass;
    private static final String TAG = "VbAdapter";
    private int resourceId;

    private ArrayList<T> list = new ArrayList<T>();

    public VbAdapter(Context context, Class<T> modelClass, Class viewHolderClass) {
        this.context = context;
        this.modelClass = modelClass;
        this.viewHolderClass = viewHolderClass;
        for (Annotation f : viewHolderClass.getDeclaredAnnotations()) {
            if (f != null)
                resourceId = ((VbAnnotation) f).resourceId();
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(resourceId, parent, false);

        try {
            Constructor<VH> constructor = viewHolderClass.getConstructor(View.class);
            return constructor.newInstance(view);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        VbModel t = (VbModel) list.get(position);


        for (Field field : t.getClass().getDeclaredFields()) {
            VbField annotation = field.getAnnotation(VbField.class);
            if (annotation != null) {
                String s = annotation.mappingId();

                try {
                    Object o = field.get(t);
                    bind(o, holder, s);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
//            Annotation[] annotations = field.getDeclaredAnnotations();
        }


    }

    private void bind(Object o, VH holder, String mappingId) {
        String value = "";
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) holder;

        if (o != null) {
            value = String.valueOf(o);
            Log.e(TAG, "bind: " + value);
        }

        for (Field field : viewHolderClass.getDeclaredFields()) {
            VbField annotation = field.getAnnotation(VbField.class);
            if (annotation != null) {
                String mappId = annotation.mappingId();
                if (mappId.equalsIgnoreCase(mappingId)) {

                    try {
                        Object viewType = field.get(viewHolder);
                        if (viewType instanceof TextView) {
                            ((TextView) viewType).setText(value);
                        } else if (viewType instanceof ImageView) {
                            Glide.with(context)
                                    .load(value)
                                    .dontAnimate()
                                    .into(((ImageView) viewType));
                        } else if (viewType instanceof Button) {
                            ((Button) viewType).setText(value);
                        } else if (viewType instanceof CheckBox) {

                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }
//            Annotation[] annotations = field.getDeclaredAnnotations();
        }

    }

    public void setList(ArrayList<T> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


}


class VbModel {

}

