package com.epam.bookshop.listusers.view.model;

import java.util.List;

public class ListUsersModel {
    private final List<UserSummaryView> users;

    public ListUsersModel(List<UserSummaryView> users) {
        super();
        this.users = users;
    }

    public List<UserSummaryView> getUsers() {
        return users;
    }

}
