package com.roman.kubik.songer.domain.user;

import javax.inject.Inject;

public class UserInteractor {

    private final UserRepository userRepository;

    @Inject
    public UserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
