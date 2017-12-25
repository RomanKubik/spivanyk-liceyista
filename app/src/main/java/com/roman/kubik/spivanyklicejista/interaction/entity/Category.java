package com.roman.kubik.spivanyklicejista.interaction.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by kubik on 12/25/17.
 */
@Entity(tableName = "category")
public class Category {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "name")
    private String categoryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
