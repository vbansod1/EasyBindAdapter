package com.vaibhav.vbadapter.interfaces;

import android.view.View;

/**
 * Created by Djey-ROHAN on 4/5/2017.
 */

public interface IHeaderFooter {
    View getHeaderView();

    View getFooterView();

    void addHeaderView(View header);

    void addFooterView(View footer);

    boolean removeHeaderView();

    boolean removeFooterView();

    boolean hasHeaderView();

    boolean hasFooterView();

    boolean isHeaderView(int position);

    boolean isFooterView(int position);
}
