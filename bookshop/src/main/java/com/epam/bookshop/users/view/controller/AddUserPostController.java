package com.epam.bookshop.users.view.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.epam.bookshop.user.service.UserWriteService;
import com.epam.bookshop.users.view.model.AddUserRequest;
import com.epam.bookshop.users.view.transform.AddUserRequestTransformer;

@Controller
public class AddUserPostController {
    private final UserWriteService userWriteService;
    private final AddUserRequestTransformer addUserRequestTransformer;

    @Autowired
    public AddUserPostController(UserWriteService userWriteService, AddUserRequestTransformer addUserRequestTransformer) {
        super();
        this.userWriteService = userWriteService;
        this.addUserRequestTransformer = addUserRequestTransformer;

    }

    @RequestMapping(value = "/addUserPost.html", method = RequestMethod.POST)
    private String createUser(@Valid AddUserRequest addUserRequest, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        String result;
        if (bindingResult.hasErrors()) {
            result = "add_user";
        } else {
            userWriteService.saveUser(addUserRequestTransformer.transformAddUserRequestToUser(addUserRequest));
            redirectAttributes.addFlashAttribute("message", String.format("User '%s' saved!", addUserRequest.getUsername()));
            result = "redirect:addUserForm.html";
        }
        return result;
    }
}
