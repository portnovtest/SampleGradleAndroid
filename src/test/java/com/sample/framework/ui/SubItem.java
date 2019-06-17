package com.sample.framework.ui;

import com.sample.framework.Platform;
import com.sample.ui.controls.Control;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Repeatable(SubItems.class)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SubItem {
    String name();
    String locator();
    Platform platform() default Platform.ANY;
    Class<? extends Control> controlType() default Control.class;
}
