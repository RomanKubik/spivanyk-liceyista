package com.roman.kubik.songer.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.roman.kubik.songer.data.song.SongEntity;
import com.roman.kubik.songer.domain.category.Category;
import com.roman.kubik.songer.domain.song.Song;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class DatabaseManager {

    private final Context context;
    private final DatabaseCopyHelper databaseCopyHelper;
    private AppDatabase appDatabase;

    @Inject
    public DatabaseManager(Context context, DatabaseCopyHelper databaseCopyHelper, AppDatabase appDatabase) {
        this.context = context;
        this.databaseCopyHelper = databaseCopyHelper;
        this.appDatabase = appDatabase;
    }

    public Completable reset() {
        return appDatabase.songDao().getAllByCategory(Category.USERS_ID)
                .flatMapCompletable(s -> {
                    String oldPath = databaseCopyHelper.getDbPath() + databaseCopyHelper.getDbName();
                    appDatabase.close();
                    File file = new File(oldPath);
                    file.delete();
                    databaseCopyHelper.createDataBase();
                    DatabaseProvider.updateAppDatabase(context);
                    appDatabase = DatabaseProvider.getAppDatabase(context);
                    for (SongEntity entity : s) {
                        appDatabase.songDao().insertOrUpdate(entity);
                    }
                    return Completable.complete();
                });
    }

}
