package com.roman.kubik.songer.domain.navigation;

import com.roman.kubik.songer.domain.song.Song;

public interface NavigationService {

    void toMainActivity();

    void toListActivity(int categoryId);

    void toSongActivity(Song song);

    void toPreferencesActivity();

    void toAddSongActivity();

    void toEditActivity(Song song);

    void toShareText(String title, String text);
}
