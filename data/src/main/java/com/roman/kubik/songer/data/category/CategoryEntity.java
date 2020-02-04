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

    public CategoryEntity(int id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }
}
