package com.vaibhav.vbadapter.Utils;

import android.view.View;

/**
 * Created by Vaibhav Bansod on 4/5/2017.
 */


public class EasyView {
    public View view;
    public String tag;

    public EasyView(String tag) {
        this.tag = tag;
    }

    public EasyView(){}
    @Override
    public boolean equals(Object obj) {
        return this.tag.equalsIgnoreCase(((EasyView) obj).tag);
    }
}
