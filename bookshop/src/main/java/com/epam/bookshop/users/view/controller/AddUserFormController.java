package com.epam.bookshop.users.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.bookshop.users.view.model.AddUserRequest;

@Controller
public class AddUserFormController {

    @ModelAttribute("addUserRequest")
    public AddUserRequest createListUsersModel(@ModelAttribute AddUserRequest addUserRequest) {
        return new AddUserRequest();
    }

    @RequestMapping(value = "/addUserForm.html", method = RequestMethod.GET)
    private String createUser() {
        return "add_user";
    }

}
