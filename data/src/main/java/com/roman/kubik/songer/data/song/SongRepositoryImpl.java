package com.roman.kubik.songer.data.song;

import android.util.Log;

import androidx.core.util.Pair;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.roman.kubik.songer.domain.category.Category;
import com.roman.kubik.songer.domain.preferences.PreferencesService;
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
    private final PreferencesService preferencesService;

    @Inject
    public SongRepositoryImpl(SongDao songDao, SongModelMapper songModelMapper, FirebaseFirestore firebaseFirestore, PreferencesService preferencesService) {
        this.songDao = songDao;
        this.songModelMapper = songModelMapper;
        this.firebaseFirestore = firebaseFirestore;
        this.preferencesService = preferencesService;
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
        return Single.fromCallable(() -> firebaseFirestore.document("meta/data").get().getResult().getLong("lastUpdate"))
                .onErrorResumeNext(e -> Single.just(System.currentTimeMillis()))
                .zipWith(preferencesService.getLastUpdatedWebSongs(), Pair::new)
                .flatMap(p -> getFromWeb(p.first, p.second));

//        preferencesService.getLastUpdatedWebSongs()
//                .zipWith()
//
//        firebaseFirestore.collection("songs").get().addOnCompleteListener(task -> {
//            if (task.isSuccessful() && task.getResult() != null) {
//               //task.getResult().getDocuments().get(0).get("lastUpdate")
//                for (DocumentSnapshot document : task.getResult().getDocuments()) {
//                    Log.d("MyTag", "getAllFromWeb: " + document.get("title"));
//                }
//                Log.w("MyTag", "Success");
//            } else {
//                Log.w("MyTag", "Error getting documents.", task.getException());
//            }
//        });
//        return Single.just(Collections.emptyList());
    }

    private Single<List<Song>> getFromWeb(long web, long local) {
        if (isWebValid(web, local)) {
            return songDao.getAllByCategory(Category.WEB_ID)
                    .toObservable()
                    .flatMapIterable(i -> i)
                    .map(songModelMapper::fromEntity)
                    .toList();
        } else {
            return Single.fromCallable(() -> firebaseFirestore.collection("song").get().getResult().getDocuments())
                    .toObservable()
                    .flatMapIterable(i -> i)
                    .map(s -> new Song(s.getId().hashCode(), s.getString("title"), s.getString("lyrics"), Category.WEB_ID))
                    .flatMapCompletable(this::insertOrUpdate)
                    .andThen(preferencesService.setLastUpdatedWebSongs(web))
                    .andThen(getAllByCategory(Category.WEB_ID));
        }
    }

    private boolean isWebValid(long web, long local) {
        return web <= local;
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
