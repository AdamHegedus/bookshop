package com.epam.bookshop.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.bookshop.user.domain.User;
import com.epam.bookshop.user.repository.dao.UserDao;
import com.epam.bookshop.user.repository.domain.UserEntity;
import com.epam.bookshop.user.service.transform.UserEntityTransformer;

@Service
public class UserSearchService {
    private final UserDao userDao;
    private final UserEntityTransformer userEntityTransformer;

    @Autowired
    public UserSearchService(UserDao userDao, UserEntityTransformer userEntityTransformer) {
        super();
        this.userDao = userDao;
        this.userEntityTransformer = userEntityTransformer;
    }

    public User findUser(Long userId) {
        return transformUserEntity(doFindUser(userId));
    }

    private User transformUserEntity(UserEntity user) {
        return userEntityTransformer.transformUserEntity(user);
    }

    private UserEntity doFindUser(Long userId) {
        return userDao.findOne(userId);
    }

    public List<User> listUsers(String email) {
        return transformUserEntities(findUserEntities(formatQuery(email)));
    }

    private String formatQuery(String email) {
        String result;
        if (email == null) {
            result = "%";
        } else {
            result = String.format("%%%s%%", email);
        }
        return result;
    }

    private List<User> transformUserEntities(Iterable<UserEntity> users) {
        return userEntityTransformer.transformUserEntities(users);
    }

    private Iterable<UserEntity> findUserEntities(String email) {
        return userDao.findByEmailIgnoreCaseLike(email);
    }
}
