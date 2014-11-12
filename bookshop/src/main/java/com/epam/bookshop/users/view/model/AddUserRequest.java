package com.epam.bookshop.users.view.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddUserRequest {
    @NotNull
    @Size(min = 2, max = 255)
    private String email;
    @NotNull
    @Size(min = 2, max = 255)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
