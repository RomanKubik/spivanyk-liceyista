package com.roman.kubik.spivanyklicejista.domain.category;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by kubik on 1/14/18.
 */

public class CategoryInteractor {

    CategoryRepository categoryRepository;

    public CategoryInteractor(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Single<List<Category>> getAll() {
        return categoryRepository.getAll();
    }

    public Maybe<Category> getById(int id) {
        return categoryRepository.getById(id);
    }
}
