package com.roman.kubik.songer.domain.favourite;

/**
 * Represents Favourite song relation
 * Created by kubik on 1/14/18.
 */

public class Favourite {

    private String id;
    private String songId;

    public Favourite(String id, String songId) {
        this.id = id;
        this.songId = songId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }
}
