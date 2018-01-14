package com.roman.kubik.spivanyklicejista;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.roman.kubik.spivanyklicejista.interaction.entity.Song;
import com.roman.kubik.spivanyklicejista.interaction.repository.AppDatabase;
import com.roman.kubik.spivanyklicejista.interaction.repository.DatabaseHelper;
import com.roman.kubik.spivanyklicejista.interaction.repository.SongDao;

import java.io.IOException;
import java.util.List;

public class EmptyActivity extends AppCompatActivity {

    private static final String TAG = EmptyActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "spivanyk.db").allowMainThreadQueries().build();

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        try {
            databaseHelper.createDataBase();
            List<Song> list = appDatabase.songDao().getAll();
            Log.d(TAG, "onCreate: " + list.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
