package com.happiness.membread.contexts.user.database.repositories;

import com.happiness.membread.contexts.user.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> { }
