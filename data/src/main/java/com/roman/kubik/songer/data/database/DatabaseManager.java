package com.roman.kubik.songer.data.database;

import io.reactivex.Completable;

public interface DatabaseManager {

    String DB_NAME = "spivanyk.db";

    Completable reset();
}
