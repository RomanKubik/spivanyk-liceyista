package com.roman.kubik.songer.domain.category;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Represents Category data model
 * Created by kubik on 1/14/18.
 */

public class Category {

    public static final int FAVOURITE_ID = -2,
            ALL_ID = -1,
            LAST_ID = 0,
            PATRIOTIC_ID = 1,
            BONFIRE_ID = 2,
            ABROAD_ID = 3,
            USERS_ID = 4,
            WEB_ID = 5;

    @IntDef({FAVOURITE_ID, ALL_ID, LAST_ID, PATRIOTIC_ID, BONFIRE_ID, ABROAD_ID, USERS_ID, WEB_ID})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CategoryId {
    }

    @CategoryId
    private int id;
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @CategoryId
    public int getId() {
        return id;
    }

    public void setId(@CategoryId int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
