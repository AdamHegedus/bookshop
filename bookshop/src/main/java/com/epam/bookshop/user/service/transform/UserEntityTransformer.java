package com.epam.bookshop.user.service.transform;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.epam.bookshop.user.domain.User;
import com.epam.bookshop.user.repository.domain.UserEntity;

@Component
public class UserEntityTransformer {
    public List<User> transformUserEntities(Iterable<UserEntity> users) {
        List<User> result = new ArrayList<User>();
        for (UserEntity user : users) {
            result.add(transformUserEntity(user));
        }
        return result;
    }

    public User transformUserEntity(UserEntity user) {
        User result = new User();
        result.setEmail(user.getEmail());
        result.setPassword(user.getPassword());
        result.setUserId(user.getId());
        return result;
    }

    public UserEntity transformUserToUserEntity(User user) {
        UserEntity result = new UserEntity();
        result.setEmail(user.getEmail());
        result.setPassword(user.getPassword());
        return result;
    }
}
