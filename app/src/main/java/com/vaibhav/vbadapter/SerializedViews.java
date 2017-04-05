package com.vaibhav.vbadapter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Vaibhav Bansod on 4/4/2017.
 */

@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.) //can use in method only.

public @interface SerializedViews {
    int resourceId();
}


@interface EasyField {
    String mappingId();
}
