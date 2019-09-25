package com.roman.kubik.songer.domain.user;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class UserInteractor {

    private final BehaviorSubject<User> userSubject = BehaviorSubject.create();

    private final UserRepository userRepository;

    @Inject
    public UserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
        refreshUser();
        
    }

    public Observable<User> getUser() {
        return userRepository.getUser();
    }

    public void refreshUser() {
        userRepository.refreshUser();
    }

    public void logOut() {
        userRepository.logOut();
    }

}
