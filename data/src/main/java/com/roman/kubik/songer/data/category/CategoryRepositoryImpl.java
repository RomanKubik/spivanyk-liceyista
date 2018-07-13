package com.roman.kubik.songer.data.category;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.roman.kubik.songer.domain.category.Category;
import com.roman.kubik.songer.domain.category.CategoryRepository;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by kubik on 1/20/18.
 */

public class CategoryRepositoryImpl implements CategoryRepository {

    private CategoryDao categoryDao;
    private CategoryModelMapper categoryModelMapper;

    public CategoryRepositoryImpl(CategoryDao categoryDao, CategoryModelMapper categoryModelMapper) {
        this.categoryDao = categoryDao;
        this.categoryModelMapper = categoryModelMapper;
    }

    @Override
    public Single<List<Category>> getAll() {
        return categoryDao.getAll()
                .map(c -> Stream.of(c)
                        .map(c1 -> categoryModelMapper.fromEntity(c1))
                        .collect(Collectors.toList()));
    }

    @Override
    public Maybe<Category> getById(int id) {
        return categoryDao.getById(id)
                .map(c -> categoryModelMapper.fromEntity(c));
    }
}
