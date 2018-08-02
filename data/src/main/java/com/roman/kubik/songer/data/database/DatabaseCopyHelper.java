package com.roman.kubik.songer.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Provides method to copy Database from assets folder to native android
 * Created by kubik on 12/25/17.
 */

public class DatabaseCopyHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseCopyHelper.class.getSimpleName();
    private static final int DB_VERSION = 1;
    private static String DB_PATH = "";
    private static String DB_NAME = "spivanyk.db";
    private final Context context;

    public DatabaseCopyHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.context = context;
    }

    public void createDataBase() throws IOException {
        //If the database does not exist, copy it from the assets.
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            copyDataBase();
            Log.d(TAG, "createDatabase: database created");
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        Log.d("dbFile", dbFile + "   " + dbFile.exists());
        return dbFile.exists();
    }

    //Copy the database from assets
    private void copyDataBase() throws IOException {
        InputStream mInput = context.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
