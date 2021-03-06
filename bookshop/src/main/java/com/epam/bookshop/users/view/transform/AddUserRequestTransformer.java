package com.epam.bookshop.users.view.transform;

import org.springframework.stereotype.Component;

import com.epam.bookshop.user.domain.User;
import com.epam.bookshop.users.view.model.AddUserRequest;

@Component
public class AddUserRequestTransformer {
    public User transformAddUserRequestToUser(AddUserRequest addUserRequest) {
        User result = new User();
        result.setUsername(addUserRequest.getUsername());
        result.setEmail(addUserRequest.getEmail());
        result.setPassword(addUserRequest.getPassword());
        result.setDateOfBirth(addUserRequest.getDateOfBirth());
        return result;

    }
}
