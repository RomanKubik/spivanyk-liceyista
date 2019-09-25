package com.roman.kubik.songer.data.database;

import android.content.Context;

import com.roman.kubik.songer.data.song.SongEntity;
import com.roman.kubik.songer.domain.category.Category;

import java.io.File;

import javax.inject.Inject;

import androidx.room.Room;
import io.reactivex.Completable;

import static com.roman.kubik.songer.data.database.DatabaseMigrations.MIGRATION_1_2;
import static com.roman.kubik.songer.data.database.DatabaseMigrations.MIGRATION_2_3;
import static com.roman.kubik.songer.data.database.DatabaseMigrations.MIGRATION_3_4;

public class DatabaseManagerImpl implements DatabaseManager {

    private final Context context;
    private final DatabaseCopyHelper databaseCopyHelper;
    private AppDatabase appDatabase;

    @Inject
    public DatabaseManagerImpl(Context context, DatabaseCopyHelper databaseCopyHelper, AppDatabase appDatabase) {
        this.context = context;
        this.databaseCopyHelper = databaseCopyHelper;
        this.appDatabase = appDatabase;
    }

    @Override
    public Completable reset() {
        return appDatabase.songDao().getAllByCategory(Category.USERS_ID)
                .flatMapCompletable(s -> {
                    String oldPath = databaseCopyHelper.getDbPath() + databaseCopyHelper.getDbName();
                    appDatabase.close();
                    File file = new File(oldPath);
                    file.delete();
                    databaseCopyHelper.createDataBase();
                    appDatabase = generateAppDatabase(context);
                    for (SongEntity entity : s) {
                        appDatabase.songDao().insertOrUpdate(entity);
                    }
                    return Completable.complete();
                });
    }

    @Override
    public Completable createDatabase() {
        return Completable.fromAction(databaseCopyHelper::createDataBase);
    }

    public static AppDatabase generateAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                .build();
    }

}
