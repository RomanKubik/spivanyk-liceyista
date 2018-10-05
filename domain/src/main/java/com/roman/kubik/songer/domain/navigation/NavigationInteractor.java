package com.roman.kubik.songer.domain.navigation;

import com.roman.kubik.songer.domain.song.Song;

public class NavigationInteractor {

    private final NavigationService navigationService;

    public NavigationInteractor(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    public void toMainActivity() {
        navigationService.toMainActivity();
    }

    public void toListActivity(int categoryId) {
        navigationService.toListActivity(categoryId);
    }

    public void toSongActivity(Song song) {
        navigationService.toSongActivity(song);
    }

    public void toPreferencesActivity() {
        navigationService.toPreferencesActivity();
    }

    public void toAddSongActivity() {
        navigationService.toAddSongActivity();
    }

    public void toEditActivity(Song song) {
        navigationService.toEditActivity(song);
    }
}
