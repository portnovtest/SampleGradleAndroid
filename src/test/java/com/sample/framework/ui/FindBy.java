package com.sample.framework.ui;

import com.sample.framework.Platform;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Repeatable(FindByList.class)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface FindBy {
    String locator();
    Platform platform() default Platform.ANY;
}
