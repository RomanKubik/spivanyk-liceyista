package com.roman.kubik.spivanyklicejista;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.roman.kubik.spivanyklicejista.interaction.repository.AppDatabase;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "spivanyk").build();
    }
}
