package com.roman.kubik.songer.domain.user;

import io.reactivex.Observable;

public interface UserRepository {
    void refreshUser();

    Observable<User> getUser();

    void logOut();
}
