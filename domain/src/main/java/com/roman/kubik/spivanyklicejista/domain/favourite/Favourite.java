package com.roman.kubik.spivanyklicejista.domain.favourite;

/**
 * Represents Favourite song relation
 * Created by kubik on 1/14/18.
 */

public class Favourite {

    private int id;
    private int songId;

    public Favourite(int id, int songId) {
        this.id = id;
        this.songId = songId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }
}
