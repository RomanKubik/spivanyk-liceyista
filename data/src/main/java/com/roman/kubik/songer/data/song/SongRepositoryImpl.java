package com.roman.kubik.songer.data.song;

import android.util.Log;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.roman.kubik.songer.domain.category.Category;
import com.roman.kubik.songer.domain.song.Song;
import com.roman.kubik.songer.domain.song.SongRepository;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Room implementation of {@link SongRepository}.
 * Created by kubik on 1/14/18.
 */

public class SongRepositoryImpl implements SongRepository {

    private final SongDao songDao;
    private final SongModelMapper songModelMapper;
    private final FirebaseFirestore firebaseFirestore;

    @Inject
    public SongRepositoryImpl(SongDao songDao, SongModelMapper songModelMapper, FirebaseFirestore firebaseFirestore) {
        this.songDao = songDao;
        this.songModelMapper = songModelMapper;
        this.firebaseFirestore = firebaseFirestore;
    }

    @Override
    public Single<List<Song>> getAll() {
        return songDao.getAll()
                .map(s -> Stream.of(s)
                        .map(songModelMapper::fromEntity).
                                collect(Collectors.toList()));
    }

    @Override
    public Single<List<Song>> getAllByCategory(int categoryId) {
        return songDao.getAllByCategory(categoryId)
                .map(s -> Stream.of(s)
                        .map(songModelMapper::fromEntity).
                                collect(Collectors.toList()));
    }

    @Override
    public Single<List<Song>> search(String query) {
        return songDao.search("%" + query + "%")
                .map(s -> Stream.of(s)
                        .map(songModelMapper::fromEntity)
                        .collect(Collectors.toList()));
    }

    @Override
    public Single<List<Song>> search(String query, @Category.CategoryId int categoryId) {
        return songDao.search("%" + query + "%", categoryId)
                .map(s -> Stream.of(s)
                        .map(songModelMapper::fromEntity)
                        .collect(Collectors.toList()));
    }

    @Override
    public Single<List<Song>> getAllFromWeb() {
        firebaseFirestore.collection("meta").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Log.d("MyTag", "getAllFromWeb: " + document.getId());
                }
                Log.w("MyTag", "Success");
            } else {
                Log.w("MyTag", "Error getting documents.", task.getException());
            }
        });
        return Single.just(Collections.emptyList());
    }

    @Override
    public Single<List<Song>> searchInWeb(String query) {
        return null;
    }

    @Override
    public Maybe<Song> getById(int id) {
        return songDao.getById(id).map(songModelMapper::fromEntity);
    }

    @Override
    public Single<Integer> getCount() {
        return songDao.getCount();
    }

    @Override
    public Single<Integer> getCountByCategory(int categoryId) {
        return songDao.getCountByCategoryId(categoryId);
    }

    @Override
    public Completable insertOrUpdate(Song song) {
        return Completable.fromAction(() -> songDao.insertOrUpdate(songModelMapper.toEntity(song)));
    }

    @Override
    public Completable delete(Song song) {
        return Completable.fromAction(() -> songDao.delete(songModelMapper.toEntity(song)));
    }
}
