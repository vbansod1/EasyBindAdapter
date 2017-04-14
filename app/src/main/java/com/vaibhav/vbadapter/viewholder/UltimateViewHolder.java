package com.vaibhav.vbadapter.viewholder;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.text.method.MovementMethod;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vaibhav.vbadapter.Utils.EasyView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vaibhav Bansod on 4/5/2017.
 */

public class UltimateViewHolder extends RecyclerView.ViewHolder implements ViewSetter<UltimateViewHolder> {
    private SparseArray<View> childViews = new SparseArray<>();
    private View itemView;
    private View localView;

    private static final String TAG = "UltimateViewHolder";
    public List<EasyView> easyViewList = new ArrayList<EasyView>();

    public UltimateViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;

        setElement(itemView);
        Log.e(TAG, "UltimateViewHolder: " + count);
    }

    int count = 0;

    private void setElement(View itemView) {

        ViewGroup layout = ((ViewGroup) itemView);
        Log.e(TAG, "setElement: " + layout.getChildCount());

        for (int i = 0; i < layout.getChildCount(); i++) {
            View childAt = layout.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                setElement(childAt);
            }
            String tag = (String) childAt.getTag();
            if (tag != null) {
                store(childAt);
            }
            count++;
        }

    }

    private void store(View view) {
        EasyView easyView = new EasyView();
        easyView.tag = (String) view.getTag();
        easyView.view = view;
        easyViewList.add(easyView);
    }

    public View getView() {
        return itemView;
    }


    public static UltimateViewHolder get(View convertView, View itemView) {
        UltimateViewHolder holder;
        if (convertView == null) {
            holder = new UltimateViewHolder(itemView);
            convertView = itemView;
            convertView.setTag(holder);
        } else {
            holder = (UltimateViewHolder) convertView.getTag();
        }
        return holder;
    }


    public <T extends View> T findViewById(int id) {
        View childView = childViews.get(id);
        if (childView == null) {
            childView = itemView.findViewById(id);
            if (childView != null)
                childViews.put(id, childView);
            else
                return null;
        }
        return (T) childView;
    }

    @Override
    public UltimateViewHolder setText(int viewId, CharSequence text) {
        TextView textView = findViewById(viewId);
        textView.setText(text);
        return this;
    }

    @Override
    public UltimateViewHolder setTextColor(int viewId, int textColor) {
        TextView view = findViewById(viewId);
        view.setTextColor(textColor);
        return this;
    }

    @Override
    public UltimateViewHolder setTextColor(int viewId, ColorStateList colorStateList) {
        TextView view = findViewById(viewId);
        view.setTextColor(colorStateList);
        return this;
    }

    @Override
    public UltimateViewHolder setMovementMethod(int viewId, MovementMethod method) {
        TextView textView = findViewById(viewId);
        textView.setMovementMethod(method);
        return this;
    }

    @Override
    public UltimateViewHolder setImageResource(int viewId, @DrawableRes int resId) {
        ImageView view = findViewById(viewId);
        view.setImageResource(resId);
        return this;
    }

    @Override
    public UltimateViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = findViewById(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    @Override
    public UltimateViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = findViewById(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    @Override
    public UltimateViewHolder setImageUri(int viewId, Uri imageUri) {
        ImageView view = findViewById(viewId);
        view.setImageURI(imageUri);
        return this;
    }

    @Override
    public UltimateViewHolder setScaleType(int viewId, ImageView.ScaleType type) {
        ImageView view = findViewById(viewId);
        view.setScaleType(type);
        return this;
    }

    @Override
    public UltimateViewHolder setBackgroundColor(int viewId, @ColorInt int bgColor) {
        View view = findViewById(viewId);
        view.setBackgroundColor(bgColor);
        return this;
    }

    @Override
    public UltimateViewHolder setBackgroundResource(int viewId, @DrawableRes int bgRes) {
        View view = findViewById(viewId);
        view.setBackgroundResource(bgRes);
        return this;
    }

    @Override
    public UltimateViewHolder setColorFilter(int viewId, ColorFilter colorFilter) {
        ImageView view = findViewById(viewId);
        view.setColorFilter(colorFilter);
        return this;
    }

    @Override
    public UltimateViewHolder setColorFilter(int viewId, int colorFilter) {
        ImageView view = findViewById(viewId);
        view.setColorFilter(colorFilter);
        return this;
    }

    @Override
    public UltimateViewHolder setAlpha(int viewId, @FloatRange(from = 0.0, to = 1.0) float value) {
        View view = findViewById(viewId);
        ViewCompat.setAlpha(view, value);
        return this;
    }

    @Override
    public UltimateViewHolder setVisibility(int viewId, int visibility) {
        View view = findViewById(viewId);
        view.setVisibility(visibility);
        return this;
    }

    @Override
    public UltimateViewHolder setMax(int viewId, int max) {
        ProgressBar view = findViewById(viewId);
        view.setMax(max);
        return this;
    }

    @Override
    public UltimateViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = findViewById(viewId);
        view.setProgress(progress);
        return this;
    }

    @Override
    public UltimateViewHolder setRating(int viewId, float rating) {
        RatingBar view = findViewById(viewId);
        view.setRating(rating);
        return this;
    }

    @Override
    public UltimateViewHolder setTag(int viewId, Object tag) {
        View view = findViewById(viewId);
        view.setTag(tag);
        return this;
    }

    @Override
    public UltimateViewHolder setEnabled(int viewId, boolean enabled) {
        View view = findViewById(viewId);
        view.setEnabled(enabled);
        return this;
    }

    @Override
    public UltimateViewHolder setAdapter(int viewId, Adapter adapter) {
        AdapterView<Adapter> view = findViewById(viewId);
        view.setAdapter(adapter);
        return this;
    }

    @Override
    public UltimateViewHolder setAdapter(int viewId, RecyclerView.Adapter adapter) {
        RecyclerView view = findViewById(viewId);
        view.setAdapter(adapter);
        return this;
    }

    @Override
    public UltimateViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = findViewById(viewId);
        view.setChecked(checked);
        return this;
    }

    @Override
    public UltimateViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        findViewById(viewId).setOnClickListener(listener);
        return this;
    }

    @Override
    public UltimateViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        findViewById(viewId).setOnLongClickListener(listener);
        return this;
    }

    @Override
    public UltimateViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        findViewById(viewId).setOnTouchListener(listener);
        return this;
    }
}
