package com.roman.kubik.songer.domain.song;

import com.roman.kubik.songer.domain.category.Category;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by kubik on 1/14/18.
 */

public interface SongRepository {
    Single<List<Song>> getAll();

    Single<List<Song>> getAllByCategory(@Category.CategoryId int categoryId);

    Single<List<Song>> search(String text);

    Single<List<Song>> search(String query, @Category.CategoryId int categoryId);

    Maybe<Song> getById(String id);

    Single<Integer> getCount();

    Single<Integer> getCountByCategory(@Category.CategoryId int categoryId);

    Completable insertOrUpdate(Song song);

    Completable delete(Song song);
}
