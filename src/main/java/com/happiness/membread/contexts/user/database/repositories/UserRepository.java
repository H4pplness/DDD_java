package com.happiness.membread.contexts.user.database.repositories;

import com.happiness.membread.contexts.user.database.entities.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,String>,CustomizedUserRepository { }
