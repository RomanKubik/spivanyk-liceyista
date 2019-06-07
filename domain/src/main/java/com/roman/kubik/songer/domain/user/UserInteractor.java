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
        
    }

    public Observable<User> getUser() {
        return userSubject;
    }

    public void refreshUser() {
        userRepository.refreshUser();
    }


}
