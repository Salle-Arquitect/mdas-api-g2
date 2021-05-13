package com.lasalle.sd2.g2.users.infrastructure.repository;

import com.lasalle.sd2.g2.users.domain.User;
import com.lasalle.sd2.g2.users.domain.UserId;
import com.lasalle.sd2.g2.users.domain.UsersRepository;
import com.lasalle.sd2.g2.users.domain.exceptions.UserNotFoundException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUsersRepository implements UsersRepository {

    private static final Map<String, User> USER_MAP = new ConcurrentHashMap<>();

    @Override
    public void save(User user) {
        USER_MAP.put(user.getUserId().toString(), user);
    }

    @Override
    public User findByUserId(UserId userId) throws UserNotFoundException {
        String id = userId.toString();
        if (!USER_MAP.containsKey(id)) {
            throw new UserNotFoundException("User " + id + " not found");
        }

        return USER_MAP.get(id);
    }
}
