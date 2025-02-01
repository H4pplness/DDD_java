package com.happiness.membread.contexts.user.database.repositories;

import com.happiness.membread.contexts.user.database.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserRepositoryImpl implements CustomizedUserRepository{
    @PersistenceContext
    EntityManager entityManager;

    @Transactional(timeout = 20,rollbackFor = Exception.class,isolation = Isolation.SERIALIZABLE)
    @Override
    public List<User> getUserByEmail(String email) {
        String queryStr = "SELECT u FROM User u WHERE u.email = :email";
        TypedQuery<User> query = entityManager.createQuery(queryStr, User.class);
        query.setParameter("email",email);
        return query.getResultList();
    }
}
