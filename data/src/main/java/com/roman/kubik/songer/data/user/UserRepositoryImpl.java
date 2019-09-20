package com.roman.kubik.songer.data.user;

import android.content.Context;
import android.content.SharedPreferences;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.roman.kubik.songer.data.preferences.Keys;
import com.roman.kubik.songer.domain.preferences.Preferences;
import com.roman.kubik.songer.domain.user.User;
import com.roman.kubik.songer.domain.user.UserPreferences;
import com.roman.kubik.songer.domain.user.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class UserRepositoryImpl implements UserRepository {

    private final SharedPreferences sharedPreferences;
    private final Context context;
    private final BehaviorSubject<User> userSubject = BehaviorSubject.create();

    @Inject
    public UserRepositoryImpl(SharedPreferences sharedPreferences, Context context) {
        this.sharedPreferences = sharedPreferences;
        this.context = context;
    }

    @Override
    public void refreshUser() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        User user = new User();
        if (firebaseUser != null) {
            user.setEmail(firebaseUser.getEmail());
            user.setFullName(firebaseUser.getDisplayName());
            user.setId(firebaseUser.getUid());
            user.setPicturePath(firebaseUser.getPhotoUrl() != null ? firebaseUser.getPhotoUrl().toString() : null);
        }
        user.setUserPreferences(getUserPreferences());
        userSubject.onNext(user);
    }

    @Override
    public Observable<User> getUser() {
        return userSubject;
    }

    @Override
    public void logOut() {
        AuthUI.getInstance()
                .signOut(context)
                .addOnFailureListener(e -> {
                    System.out.println(e.getMessage());
                })
                .addOnCompleteListener(task -> refreshUser());
    }

    private UserPreferences getUserPreferences() {
        UserPreferences userPreferences = new UserPreferences();
//        userPreferences.setChordsVisible(sharedPreferences.getBoolean(Keys.SHOW_CHORDS, true));
//        userPreferences.setSelectedInstrument(sharedPreferences.getString(Keys.SELECTED_INSTRUMENT, Preferences.GUITAR));
        return userPreferences;
    }
}
