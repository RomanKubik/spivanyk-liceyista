package com.roman.kubik.spivanyklicejista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.roman.kubik.spivanyklicejista.data.database.DatabaseCopyHelper;
import com.roman.kubik.spivanyklicejista.data.database.DatabaseDaoProvider;
import com.roman.kubik.spivanyklicejista.data.song.SongModelMapper;
import com.roman.kubik.spivanyklicejista.data.song.SongRepositoryImpl;
import com.roman.kubik.spivanyklicejista.domain.song.interaction.SongsInteractor;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EmptyActivity extends AppCompatActivity {

    private static final String TAG = EmptyActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        SongsInteractor songsInteractor =new SongsInteractor(new SongRepositoryImpl(DatabaseDaoProvider.getInstance(this).songDao(), new SongModelMapper()));

        DatabaseCopyHelper databaseHelper = new DatabaseCopyHelper(this);
        try {
            databaseHelper.createDataBase();

            songsInteractor.getAll().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> {
                        Toast.makeText(this, "Size: " +  s.size(), Toast.LENGTH_SHORT).show();
                    }, t -> {
                        Toast.makeText(this, "Throwable: " +  t.getMessage(), Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
