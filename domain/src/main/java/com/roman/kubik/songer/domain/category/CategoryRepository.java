package com.roman.kubik.songer.domain.category;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by kubik on 1/14/18.
 */

public interface CategoryRepository {

    Single<List<Category>> getAll();

    Maybe<Category> getById(@Category.CategoryId int id);


}
