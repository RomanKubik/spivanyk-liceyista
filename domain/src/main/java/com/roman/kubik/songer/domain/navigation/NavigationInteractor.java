package com.roman.kubik.songer.domain.navigation;

import com.roman.kubik.songer.domain.song.Song;
import com.roman.kubik.songer.domain.song.SongRepository;

import java.util.Random;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NavigationInteractor {

    private final Random random = new Random();
    private final NavigationService navigationService;
    private final SongRepository songRepository;

    public NavigationInteractor(NavigationService navigationService, SongRepository songRepository) {
        this.navigationService = navigationService;
        this.songRepository = songRepository;
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

    public void toRandomSong() {
        Disposable disposable = songRepository.getAll()
                .map(l -> l.get(random.nextInt(l.size())))
                .subscribeOn(Schedulers.io())
                .subscribe(navigationService::toSongActivity);
    }

    public void toShareText(Song song) {
        navigationService.toShareText(song.getTitle(), song.getLyrics());
    }

    public void restart() {
        navigationService.restart();
    }

    public void toSignIn(int requestCode) {
        navigationService.toSignIn(requestCode);
    }

    public void navigateUp() {
        navigationService.navigateUp();
    }
}
