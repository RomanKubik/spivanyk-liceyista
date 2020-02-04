package com.roman.kubik.songer.data.song.remote;

import com.roman.kubik.songer.domain.category.Category;
import com.roman.kubik.songer.domain.song.RemoteSongRepository;
import com.roman.kubik.songer.domain.song.Song;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class RemoteSongRepositoryImpl implements RemoteSongRepository {

    private static final String BASE_URL = "https://mychords.net";
    private static final String SEARCH_URL = "/search?q=";

    private static final String SONG_TABLE_ITEM = "a.b-listing__item__link";

    @Inject
    public RemoteSongRepositoryImpl() {
    }

    @Override
    public Single<List<Song>> getAll() {
        return Single.error(new RuntimeException("Get all is not supported with this repository"));
    }

    @Override
    public Single<List<Song>> search(String query) {
        return Single.fromCallable(() -> {
            Document document = Jsoup.connect(BASE_URL + SEARCH_URL + query.replaceAll(" ", "+")).get();
            Elements elements = document.select(SONG_TABLE_ITEM);

            List<Song> songs = new ArrayList<>();

            for (int i = 0; i < elements.size(); i++) {
                try {
                    songs.add(new Song(-1, elements.get(i).text(), null, Category.WEB_ID));
                } catch (Exception e) {
                    /* ignore exception */
                }
            }


            return songs;
        });
    }

    @Override
    public Maybe<Song> getSong(String id) {
        return null;
    }
}
