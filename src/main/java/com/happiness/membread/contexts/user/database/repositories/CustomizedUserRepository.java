package com.happiness.membread.contexts.user.database.repositories;

import com.happiness.membread.contexts.user.database.entities.User;

import java.util.List;

public interface CustomizedUserRepository {
    List<User> getUserByEmail(String email);
}
