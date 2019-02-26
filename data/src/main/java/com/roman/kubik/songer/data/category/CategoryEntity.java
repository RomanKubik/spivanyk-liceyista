package com.roman.kubik.songer.data.category;

/**
 * Created by kubik on 1/14/18.
 */

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "category")
public class CategoryEntity {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "name")
    @NonNull
    private String name;

    public CategoryEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
