package com.roman.kubik.songer.domain.deletion;

public class Deletion {
    private int id;
    private int songId;
    private long timestamp;

    public Deletion(int id, int songId, long timestamp) {
        this.id = id;
        this.songId = songId;
        this.timestamp = timestamp;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
