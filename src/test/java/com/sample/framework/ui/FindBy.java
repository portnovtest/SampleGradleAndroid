package com.sample.framework.ui;

import com.sample.framework.Platform;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Repeatable(FindByList.class)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface FindBy {
    String locator();
    Platform platform() default Platform.ANY;
    String itemLocator() default "";
    String scrollTo() default "";
    ScrollTo scrollDirection() default ScrollTo.TOP_BOTTOM;
    String format() default "";
    boolean excludeFromSearch() default false;

}
