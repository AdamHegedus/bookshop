package com.epam.bookshop.listusers.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.bookshop.listusers.view.model.ListUsersModel;
import com.epam.bookshop.listusers.view.model.ListUsersRequest;
import com.epam.bookshop.listusers.view.model.UserSummaryView;
import com.epam.bookshop.listusers.view.transform.UserTransformer;
import com.epam.bookshop.user.domain.User;
import com.epam.bookshop.user.service.UserSearchService;

@Controller
public class ListUsersController {
    public static final String REQUEST_MAPPING = "/users.html";
    private final UserSearchService userSearchService;
    private final UserTransformer userTransformer;

    @Autowired
    public ListUsersController(UserSearchService userSearchService, UserTransformer userTransformer) {
        super();
        this.userSearchService = userSearchService;
        this.userTransformer = userTransformer;
    }

    @ModelAttribute("listUsersModel")
    public ListUsersModel createListUsersModel(ListUsersRequest listUsersRequest) {
        List<User> users = listUsers(listUsersRequest);
        List<UserSummaryView> userViews = transformUsers(users);
        return initListUsersModel(userViews);
    }

    @RequestMapping(value = REQUEST_MAPPING, method = RequestMethod.GET)
    public String showUsers() {
        return "users";
    }

    private List<UserSummaryView> transformUsers(List<User> users) {
        return userTransformer.transformUsersToSummaries(users);
    }

    private ListUsersModel initListUsersModel(List<UserSummaryView> userViews) {
        return new ListUsersModel(userViews);
    }

    private List<User> listUsers(ListUsersRequest searchUsersRequest) {
        return userSearchService.listUsers(searchUsersRequest.getEmail());
    }

}
