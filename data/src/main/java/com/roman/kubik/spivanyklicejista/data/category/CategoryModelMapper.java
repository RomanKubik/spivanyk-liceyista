package com.roman.kubik.spivanyklicejista.data.category;

import com.roman.kubik.spivanyklicejista.domain.EntityModelMapper;
import com.roman.kubik.spivanyklicejista.domain.category.Category;

/**
 * Created by kubik on 1/20/18.
 */

public class CategoryModelMapper implements EntityModelMapper<CategoryEntity, Category> {

    @Override
    public Category fromEntity(CategoryEntity from) {
        return new Category(from.getId(), from.getName());
    }

    @Override
    public CategoryEntity toEntity(Category from) {
        return new CategoryEntity(from.getId(), from.getName());
    }
}
