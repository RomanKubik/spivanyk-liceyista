package com.roman.kubik.spivanyklicejista.data.category;

/**
 * Created by kubik on 1/14/18.
 */

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "category")
public class CategoryEntity {

    @PrimaryKey
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
