package com.roman.kubik.songer.domain.song;

import com.annimon.stream.Objects;

/**
 * Represents Song data model
 * Created by kubik on 1/14/18.
 */

public class Song {

    private int id;
    private String title;
    private String lyrics;
    private int categoryId;

    public Song(int id, String title, String lyrics, int categoryId) {
        this.id = id;
        this.title = title;
        this.lyrics = lyrics;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return id == song.id &&
                categoryId == song.categoryId &&
                Objects.equals(title, song.title) &&
                Objects.equals(lyrics, song.lyrics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, lyrics, categoryId);
    }
}
