package com.roman.kubik.songer.data.song.remote;

import com.roman.kubik.songer.domain.song.RemoteSongRepository;
import com.roman.kubik.songer.domain.song.Song;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class RemoteSongRepositoryImpl implements RemoteSongRepository {

    private static final String BASE_URL = "http://m.pisni.org.ua/?phrase=";
    private static final String URL_ENCODING = "windows-1251";

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
            String encodedQuery = URLEncoder.encode(query, URL_ENCODING);
            Document document = Jsoup.connect(BASE_URL + encodedQuery).get();
            document.select("tr");

            return Collections.emptyList();
        });
    }

    @Override
    public Maybe<Song> getSong(String id) {
        return null;
    }
}
