package com.vaibhav.vbadapter.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.vaibhav.vbadapter.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Djey-ROHAN on 4/5/2017.
 */

public class AttributeParser {

    private AttributeParserFactory mFactory;
    private Map<Integer, HashMap<Integer, String>> mAttributeList;

    private class AttributeParserFactory implements LayoutInflater.Factory{
        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            String id = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "mappingId");

            if(id != null){
                // String with the reference character "@", so we strip it to keep only the reference
                id = id.replace("@", "");

                TypedArray libraryStyledAttributeList = context.obtainStyledAttributes(attrs, R.styleable.easyfield);
                HashMap<Integer, String> libraryViewAttribute = new HashMap<Integer, String>();
                int i = 0;

                for(int attribute : R.styleable.easyfield){
                    String attributeValue = libraryStyledAttributeList.getString(i);

                    if(attributeValue != null)
                        libraryViewAttribute.put(attribute, attributeValue);

                    i++;
                }

                if(!libraryViewAttribute.isEmpty())
                    mAttributeList.put(Integer.valueOf(id), libraryViewAttribute);

                libraryStyledAttributeList.recycle();
            }

            return null;
        }

    }

    public AttributeParser(){
        mAttributeList = new HashMap<Integer, HashMap<Integer, String>>();
        mFactory = new AttributeParserFactory();
    }

    public void clear() {
        mAttributeList.clear();
    }

    public LayoutInflater getLayoutInflater(LayoutInflater inflater) {
        clear();
        LayoutInflater layoutInflater = inflater.cloneInContext(inflater.getContext());
        layoutInflater.setFactory(mFactory);

        return layoutInflater;
    }

    public void setFactory(LayoutInflater inflater){
        inflater.cloneInContext(inflater.getContext()).setFactory(mFactory);
    }

    public void setViewAttribute(Activity activity) {
        for(Map.Entry<Integer, HashMap<Integer, String>> attribute : mAttributeList.entrySet())
            if(activity.findViewById((Integer) attribute.getKey()) != null)
                activity.findViewById((Integer) attribute.getKey()).setTag(attribute.getValue());

    }

    public void setViewAttribute(View view) {
        for(Map.Entry<Integer, HashMap<Integer, String>> attribute : mAttributeList.entrySet())
            if(view.findViewById((Integer) attribute.getKey()) != null)
                view.findViewById((Integer) attribute.getKey()).setTag(attribute.getValue());
    }

    public Map<Integer, HashMap<Integer, String>> getAttributeList() {
        return mAttributeList;
    }

    public void setAttributeList(Map<Integer, HashMap<Integer, String>> attributeList) {
        this.mAttributeList = attributeList;
    }
}
