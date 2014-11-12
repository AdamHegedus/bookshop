package com.epam.bookshop.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.bookshop.user.domain.User;
import com.epam.bookshop.user.repository.dao.UserDao;
import com.epam.bookshop.user.service.transform.UserEntityTransformer;

@Service
public class UserWriteService {
    private final UserDao userDao;
    private final UserEntityTransformer userEntityTransformer;

    @Autowired
    public UserWriteService(UserDao userDao, UserEntityTransformer userEntityTransformer) {
        super();
        this.userDao = userDao;
        this.userEntityTransformer = userEntityTransformer;
    }

    public void saveUser(User user) {
        userDao.save(userEntityTransformer.transformUserToUserEntity(user));
    }

}
